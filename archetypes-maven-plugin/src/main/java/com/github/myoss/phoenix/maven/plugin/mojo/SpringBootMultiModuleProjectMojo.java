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

package com.github.myoss.phoenix.maven.plugin.mojo;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.alibaba.fastjson.JSON;
import com.github.myoss.phoenix.core.lang.io.FileUtil;
import com.github.myoss.phoenix.maven.plugin.config.Module;

/**
 * 生成 SpringBoot 多模块项目代码
 *
 * @author Jerry.Chen
 * @since 2018年7月9日 下午3:11:28
 */
@Mojo(name = "springBootMultiModuleProject", requiresProject = false)
public class SpringBootMultiModuleProjectMojo extends SpringBootSingleProjectMojo {
    /**
     * 模板文件夹名字
     */
    public static final String TEMPLATE_DIRECTORY         = "spring-boot-multi-module-project";
    /**
     * 模块的模板文件夹名字
     */
    public static final String MODULES_TEMPLATE_DIRECTORY = "module";

    /**
     * 项目多模块配置
     */
    @Parameter(property = "modules", required = false)
    protected List<Module>     modules;
    /**
     * 项目多模块配置（json字符串）
     *
     * <pre>
     * -DmodulesJson='[{"name":"order-web","moduleType":"spring-boot"},{"name":"order-service","moduleType":"normal"}]'
     * </pre>
     */
    @Parameter(property = "modulesJson", required = false)
    protected String           modulesJson;

    @Override
    public void execute() {
        if (!init) {
            init = true;
            initConfiguration();
            initTemplateEngine();
        }

        // 生成根目录文件
        getLog().info("begin generate " + TEMPLATE_DIRECTORY);
        String rootTemplateDirectory = TEMPLATE_DIRECTORY;
        Map<String, InputStream> rootTemplateFiles = getTemplateFiles(rootTemplateDirectory, true);
        generateFiles(data, rootTemplateDirectory, rootTemplateFiles, rootPath);

        // 生成子模块
        String modulesTemplateDirectory = rootTemplateDirectory + File.separator + MODULES_TEMPLATE_DIRECTORY;
        generateSubModuleFiles(modulesTemplateDirectory, modules);
    }

    @Override
    public void initConfiguration() {
        super.initConfiguration();
        if (this.modulesJson != null) {
            List<Module> modules = JSON.parseArray(modulesJson, Module.class);
            if (this.modules != null) {
                this.modules.addAll(modules);
            } else {
                this.modules = modules;
            }
        }
        this.data.put("modules", this.modules);
    }

    @Override
    public boolean skipGenerateSomeFile(String templateDirectory, String templateFile, InputStream templateContent,
                                        Path targetFile) {
        return TEMPLATE_DIRECTORY.equals(templateDirectory) && MODULES_TEMPLATE_DIRECTORY.equals(templateFile);
    }

    /**
     * 生成子模块文件
     *
     * @param modulesTemplateDirectory 模块的模板文件夹名字
     * @param modules 项目多模块配置
     * @return 返回所有的模版文件
     */
    public Map<String, InputStream> generateSubModuleFiles(String modulesTemplateDirectory, List<Module> modules) {
        Map<String, InputStream> allTemplateFiles = new LinkedHashMap<>();
        for (Module module : modules) {
            String moduleTemplateDirectory = modulesTemplateDirectory + File.separator + module.getModuleType();
            getLog().info("begin generate " + moduleTemplateDirectory);
            String moduleName = module.getName();
            Path moduleSavePath = rootPath.resolve(moduleName);
            FileUtil.createDirectories(moduleSavePath);
            data.put("moduleArtifactId", moduleName);
            Map<String, InputStream> templateFiles = getTemplateFiles(moduleTemplateDirectory);
            allTemplateFiles.putAll(templateFiles);
            generateFiles(data, moduleTemplateDirectory, templateFiles, moduleSavePath);
        }
        return allTemplateFiles;
    }
}
