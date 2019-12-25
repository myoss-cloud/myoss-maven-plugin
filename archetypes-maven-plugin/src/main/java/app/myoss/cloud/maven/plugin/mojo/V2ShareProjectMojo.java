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

import java.io.InputStream;
import java.util.Map;

import org.apache.maven.plugins.annotations.Mojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 生成第二版 share 项目代码
 *
 * @author Jerry.Chen
 * @since 2018年7月9日 下午3:11:28
 */
@Setter
@Getter
@Mojo(name = "v2ShareProject", requiresProject = false)
public class V2ShareProjectMojo extends SpringBootSingleProjectMojo {
    /**
     * 模板文件夹名字
     */
    public static final String TEMPLATE_DIRECTORY = "v2-share-project";

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
}
