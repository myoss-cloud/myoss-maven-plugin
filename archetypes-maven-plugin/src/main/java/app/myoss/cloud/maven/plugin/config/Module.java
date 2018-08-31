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

package app.myoss.cloud.maven.plugin.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 项目模块配置
 *
 * @author Jerry.Chen
 * @since 2018年7月9日 下午4:48:06
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Module extends AbstractPropertyHolder {
    /**
     * 模块名字
     */
    private String name;
    /**
     * normal: 普通项目; spring-boot: SpringBoot项目
     */
    private String moduleType;
}
