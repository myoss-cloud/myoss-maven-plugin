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

/**
 * 类package-info的实现描述：Biz业务测试代码
 *
 * @author ${configuration.author}
 * @since ${configuration.generateDate}
 */
package ${package}.biz;

