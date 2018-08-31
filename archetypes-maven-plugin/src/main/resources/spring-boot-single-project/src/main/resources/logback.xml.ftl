<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="600 seconds" debug="false">
    <property name="logging.level" value="INFO"/>
    <property name="project.name" value="${artifactId}"/>
    <property name="project.name.monitor" value="${artifactId?replace("-", "")}"/>

    <include resource="app/myoss/cloud/apm/log/logback/logback-file-appender.xml"/>
    <#if configuration.useMybatis>

    <logger name="${package}.component.mybatis">
        <if condition='",prd".indexOf(property("DEPLOY_ENV")) > 0'>
            <then>
                <!-- 生产环境 -->
                <level value="WARN"/>
            </then>
            <else>
                <!-- 开发/测试/预发 -->
                <level value="DEBUG"/>
            </else>
        </if>
    </logger>

    </#if>

    <root>
        <level value="${r"${logging.level}"}"/>
        <appender-ref ref="errorAppenderAsync"/>
        <appender-ref ref="infoAppenderAsync"/>
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>