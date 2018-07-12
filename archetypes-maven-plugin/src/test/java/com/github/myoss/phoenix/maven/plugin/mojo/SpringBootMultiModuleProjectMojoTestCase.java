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

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 * {@link SpringBootSingleProjectMojo} 测试类，第一次运行，需要先执行
 * <code>mvn clean package</code>
 *
 * @author Jerry.Chen
 * @since 2018年7月2日 上午11:59:07
 */
public class SpringBootMultiModuleProjectMojoTestCase extends AbstractMojoTestCase {
    public void testGenerate() throws Exception {
        String pluginPom = getBasedir() + "/src/test/resources/spring-boot-multi-module-project-test/plugin-pom.xml";
        SpringBootMultiModuleProjectMojo mojo = (SpringBootMultiModuleProjectMojo) lookupMojo(
                "springBootMultiModuleProject", pluginPom);
        assertNotNull(mojo);
        mojo.execute();
    }
}
