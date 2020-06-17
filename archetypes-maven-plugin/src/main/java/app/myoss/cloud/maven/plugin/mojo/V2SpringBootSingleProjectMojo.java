/*
 * Copyright 2018-2018 https://github.com/myoss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package app.myoss.cloud.maven.plugin.mojo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import app.myoss.cloud.core.constants.MyossConstants;
import app.myoss.cloud.core.exception.BizRuntimeException;
import app.myoss.cloud.core.lang.io.FileUtil;
import app.myoss.cloud.core.lang.io.StreamUtil;
import app.myoss.cloud.core.lang.json.JsonApi;
import app.myoss.cloud.core.utils.NameStyle;
import app.myoss.cloud.maven.plugin.config.Configuration;
import app.myoss.cloud.maven.plugin.template.TemplateEngine;
import app.myoss.cloud.maven.plugin.template.impl.FreemarkerTemplateImpl;
import app.myoss.cloud.web.utils.MavenUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 生成第二版 SpringBoot 单模块项目代码
 *
 * @author Jerry.Chen
 * @since 2018年7月2日 上午11:55:22
 */
@Setter
@Getter
@Mojo(name = "v2SpringBootSingleProject", requiresProject = false)
public class V2SpringBootSingleProjectMojo extends AbstractMojo {
    /**
     * 模板文件夹名字
     */
    public static final String        TEMPLATE_DIRECTORY          = "v2-spring-boot-single-project";
    /**
     * JAVA 文件路径
     */
    public static final String[]      JAVA_PATH                   = new String[] { "src/main/java", "src/test/java" };
    /**
     * JAR 文件
     */
    public static final String        JAR                         = "jar";
    /**
     * 临时占位文件，对于 git 仓库，如果目录是空的，此目录就会被忽略掉，导致某些空白的目录在服务器打包的时候，因为没有而无法被生成
     */
    public static final String        TMP_PLACEHOLDER_FILE        = "tmp-placeholder-file.ignore";
    /**
     * Java main class 文件名
     */
    public static final String        JAVA_MAIN_CLASS_FILE        = "JAVA-MAIN-ClASS.java.ftl";
    /**
     * Java main class name 模版文件变量名
     */
    public static final String        JAVA_MAIN_CLASS_NAME        = "JAVA_MAIN_ClASS";

    protected boolean                 init                        = false;
    protected TemplateEngine          templateEngine;
    protected HashMap<String, Object> data                        = new HashMap<>();
    protected Path                    rootPath;
    protected boolean                 skipFindMyossReleaseVersion = false;
    protected RestTemplate            restTemplate                = new RestTemplate();
    protected String                  nexusRepositoryUrl          = "https://repo1.maven.org/maven2";

    /**
     * 项目文件保存的目录
     */
    @Parameter(property = "directory", required = true)
    protected File                    directory;
    /**
     * 项目的 groupId
     */
    @Parameter(property = "groupId", required = true)
    protected String                  groupId;
    /**
     * 项目的 artifactId
     */
    @Parameter(property = "artifactId", required = true)
    protected String                  artifactId;
    /**
     * 项目的 version
     */
    @Parameter(property = "version", defaultValue = "1.0.0")
    protected String                  version;
    /**
     * 项目的父类package name
     */
    @Parameter(property = "rootPackageName", required = true)
    protected String                  rootPackageName;
    /**
     * 作者名字
     */
    @Parameter(property = "author", required = true)
    protected String                  author;
    /**
     * 版权信息注释
     */
    @Parameter(property = "copyright", required = false)
    protected String                  copyright;
    /**
     * 自定义配置信息
     */
    @Parameter(property = "configuration", required = false)
    protected Configuration           configuration;
    /**
     * 自定义配置信息（json字符串）
     */
    @Parameter(property = "configurationJson", required = false)
    protected String                  configurationJson;

    /**
     * 执行生成文件
     */
    @Override
    public void execute() {
        if (!init) {
            init = true;
            initConfiguration();
            initTemplateEngine();
        }

        getLog().info("begin generate " + TEMPLATE_DIRECTORY);
        Map<String, InputStream> templateFiles = getTemplateFiles(TEMPLATE_DIRECTORY);
        generateFiles(data, TEMPLATE_DIRECTORY, templateFiles, rootPath);
    }

    /**
     * 初始化配置信息
     */
    public void initConfiguration() {
        getLog().info("initConfiguration");
        rootPath = directory.toPath().resolve(artifactId);

        if (StringUtils.isNotBlank(configurationJson)) {
            configuration = JsonApi.fromJson(configurationJson, Configuration.class);
        }
        if (configuration == null) {
            configuration = new Configuration();
        }
        data.put("configuration", configuration);
        data.put("groupId", groupId);
        data.put("artifactId", artifactId);
        data.put("version", version);
        data.put("package", rootPackageName);
        data.put(JAVA_MAIN_CLASS_NAME, getJavaMainClassName());

        if (!skipFindMyossReleaseVersion) {
            String myossSpringBootParentReleaseVersion = MavenUtils.findReleaseVersionInNexus(restTemplate,
                    nexusRepositoryUrl, "app.myoss.cloud", "myoss-spring-boot-parent");
            data.put("myossSpringBootParentReleaseVersion", myossSpringBootParentReleaseVersion);
        }

        configuration.setAuthor(author);
        configuration.setCopyright(copyright);
        convertConfigurationArgs(configuration);
    }

    /**
     * 获取 Java main class 文件名
     *
     * @return Java main class 文件名
     */
    public String getJavaMainClassName() {
        List<String> collect = Stream.of(this.artifactId.split("-"))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        String transform = NameStyle.PASCAL_CASE
                .transform(CollectionUtils.isEmpty(collect) ? this.artifactId : collect.get(0));
        return transform + "Application";
    }

    /**
     * 转换 {@link Configuration} 中的参数
     *
     * @param configuration 自定义配置信息
     */
    public void convertConfigurationArgs(Configuration configuration) {
        for (Entry<String, Object> entry : configuration.getProperties().entrySet()) {
            if ((entry.getValue() instanceof String)) {
                if ("true".equals(entry.getValue())) {
                    entry.setValue(true);
                } else if ("false".equals(entry.getValue())) {
                    entry.setValue(false);
                }
            }
        }
    }

    /**
     * 初始化模版引擎
     */
    public void initTemplateEngine() {
        if (skipInitTemplateEngine()) {
            return;
        }
        getLog().info("initTemplateEngine");
        // init template engine
        if (templateEngine == null) {
            getLog().info("init " + FreemarkerTemplateImpl.class);
            templateEngine = new FreemarkerTemplateImpl();
        }
        templateEngine.init(configuration);
    }

    /**
     * 从 Jar包/class目录 中获取文件模版
     *
     * @param templateDirectory 模板文件夹名字
     * @param excludeChildDirectory 排除 <code>templateDirectory</code> 子目录文件
     * @return 模版文件夹列表
     */
    public Map<String, InputStream> getTemplateFiles(String templateDirectory, boolean excludeChildDirectory) {
        URL singleProject = getClass().getResource("/" + templateDirectory);
        getLog().info(singleProject.toString());
        Map<String, InputStream> templateFiles;
        if (JAR.equals(singleProject.getProtocol())) {
            // 从 Jar 包中获取文件模版
            templateFiles = FileUtil.getFilesFromJar(singleProject, templateDirectory, excludeChildDirectory, true);
        } else {
            // 从 class 目录中获取文件模板
            Path path = FileUtil.toPath(singleProject);
            templateFiles = FileUtil.getFilesFromDirectory(path, excludeChildDirectory, true);
        }
        return templateFiles;
    }

    /**
     * 从 Jar包/class目录 中获取文件模版
     *
     * @param templateDirectory 模板文件夹名字
     * @return 模版文件夹列表
     */
    public Map<String, InputStream> getTemplateFiles(String templateDirectory) {
        return getTemplateFiles(templateDirectory, false);
    }

    /**
     * 生成文件
     *
     * @param data 配置数据
     * @param templateDirectory 模板文件夹名字
     * @param templateFiles 模版文件
     * @param saveRootPath 生成文件保存的根目录
     */
    public void generateFiles(HashMap<String, Object> data, String templateDirectory,
                              Map<String, InputStream> templateFiles, Path saveRootPath) {
        getLog().info("generate files save to path: " + saveRootPath);
        String templateSuffix = templateEngine.getTemplateSuffix();
        String separator = templateDirectory + "/";
        for (Entry<String, InputStream> entry : templateFiles.entrySet()) {
            // 替换 windows 文件分割符合为 unix 风格
            String sourceFile = entry.getKey().replace("\\", "/");
            InputStream sourceContent = entry.getValue();
            getLog().info("process fileName: " + sourceFile);
            String templateFile = StringUtils.substringAfter(sourceFile, separator);
            Path targetFile = resolveJavaPath(saveRootPath, rootPackageName, templateFile);
            if (skipGenerateSomeFile(templateDirectory, templateFile, sourceContent, targetFile)) {
                // 不生成某些文件
                getLog().info("======> skipGenerateFile: " + targetFile.getFileName());
                continue;
            }

            FileUtil.createDirectories(targetFile.getParent());
            if (templateFile.endsWith(TMP_PLACEHOLDER_FILE)) {
                getLog().info("======> skipIgnoreFile: " + targetFile.getFileName());
            } else if (templateFile.endsWith(templateSuffix)) {
                // 模版引擎生成
                String saveFilePath;
                if (StringUtils.endsWith(templateFile, JAVA_MAIN_CLASS_FILE)) {
                    saveFilePath = targetFile.getParent().resolve(data.get(JAVA_MAIN_CLASS_NAME) + ".java").toString();
                } else {
                    saveFilePath = StringUtils.removeEnd(targetFile.toString(), templateSuffix);
                }
                templateEngine.writer(separator + templateFile, saveFilePath, data);
                generateFileAfter(saveFilePath, templateFile);
            } else if (sourceContent == null) {
                // 文件夹
                FileUtil.createDirectories(targetFile);
            } else {
                // 普通文件
                try {
                    String content = StreamUtil.copyToString(sourceContent, MyossConstants.DEFAULT_CHARSET);
                    FileUtils.writeStringToFile(targetFile.toFile(), content, MyossConstants.DEFAULT_CHARSET);
                    generateFileAfter(targetFile.toString(), templateFile);
                } catch (IOException e) {
                    throw new BizRuntimeException(e);
                }
            }
        }
    }

    /**
     * 检查是否跳过初始化模版引擎
     *
     * @return true: 不初始化; false： 初始化
     */
    public boolean skipInitTemplateEngine() {
        return false;
    }

    /**
     * 检查是否不生成某些文件
     *
     * @param templateDirectory 模板文件夹名字
     * @param templateFile 模版文件名（包含相对路径）
     * @param templateContent 原始文件的内容
     * @param targetFile 生成的文件名
     * @return true: 不生成; false: 生成
     */
    public boolean skipGenerateSomeFile(String templateDirectory, String templateFile, InputStream templateContent,
                                        Path targetFile) {
        return false;
    }

    /**
     * 为 Java 文件生成父类package name
     *
     * @param targetPath 保存到哪个目录下面
     * @param basePackage 父类package name
     * @param templateFile 模版文件（保护文件路径）
     * @return 生成新的文件路径
     */
    public Path resolveJavaPath(Path targetPath, String basePackage, String templateFile) {
        for (String item : JAVA_PATH) {
            if (templateFile.startsWith(item)) {
                String replace = templateFile.replace(item, item + "/" + basePackage.replace(".", "/"));
                return targetPath.resolve(replace);
            }
        }
        return targetPath.resolve(templateFile);
    }

    /**
     * 获取模版文件的相对路径
     *
     * @param templateDirectory 模板文件夹名字
     * @param templateFiles 模版文件
     * @return 模版文件名字集合
     */
    public Set<String> templateFilesRemoveDirectoryPath(String templateDirectory,
                                                        Map<String, InputStream> templateFiles) {
        String separator = templateDirectory + "/";
        return templateFiles.entrySet()
                .stream()
                .map(entry -> StringUtils.substringAfter(entry.getKey().replace("\\", "/"), separator))
                .collect(Collectors.toSet());
    }

    /**
     * 生成文件后置逻辑处理，可在子类中重写
     *
     * @param filePath 生成文件保存目录
     * @param templateFile 模版文件
     */
    public void generateFileAfter(String filePath, String templateFile) {
    }
}
