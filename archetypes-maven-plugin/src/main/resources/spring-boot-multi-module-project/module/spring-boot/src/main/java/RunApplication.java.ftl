<#if configuration.copyright!?length gt 0>
/*
    <#list configuration.copyright?split("\n") as item>
        <#if item!?length gt 0>
 * ${item}
        <#else>
 *
        </#if>
    </#list>
 */

</#if>

package ${package};

import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.myoss.cloud.web.spring.boot.BootApplication;
import lombok.extern.slf4j.Slf4j;


/**
 * 项目启动类
 *
 * @author ${configuration.author}
 * @since ${configuration.generateDate}
 */
@Slf4j
@SpringBootApplication
public class RunApplication {
    /**
     * 项目启动类
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        BootApplication.run(log, RunApplication.class, args);
    }
}
