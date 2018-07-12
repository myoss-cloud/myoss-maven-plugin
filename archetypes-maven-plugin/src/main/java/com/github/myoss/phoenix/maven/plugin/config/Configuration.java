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

package com.github.myoss.phoenix.maven.plugin.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义全局配置
 *
 * @author Jerry.Chen
 * @since 2018年5月4日 下午6:12:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Configuration extends AbstractPropertyHolder {
    /**
     * 生成文件保存的根目录
     */
    private String  rootOutputPath;

    /**
     * 版权信息注释
     */
    private String  copyright;
    /**
     * 当前时间的年份，例如：2018
     */
    private String  todayYear;
    /**
     * 生成时间，默认是当前时间
     */
    private String  generateDate;
    /**
     * 作者名字
     */
    private String  author;

    /**
     * 使用 Mybatis
     */
    private boolean useMybatis = false;

    public Configuration() {
    }
}
