<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.github.myoss</groupId>
        <artifactId>phoenix-parent</artifactId>
        <version>${phoenixParentReleaseVersion}</version>
    </parent>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <name>${artifactId}</name>
    <version>${version}</version>
    <packaging>jar</packaging>

    <properties>
        <${artifactId}.version>${version}</${artifactId}.version>
        <phoenix-parent.version>${phoenixParentReleaseVersion}</phoenix-parent.version>
        <phoenix-core.version>${phoenixCoreReleaseVersion}</phoenix-core.version>
        <phoenix-mybatis.version>${phoenixMybatisReleaseVersion}</phoenix-mybatis.version>
    </properties>

    <modules>
<#list modules as module>
    <module>${module.name}</module>
</#list>
    </modules>

    <dependencies>
        <!-- myoss cloud dependencies start -->
        <dependency>
            <groupId>com.github.myoss</groupId>
            <artifactId>phoenix-core</artifactId>
            <version>${r"${phoenix-core.version}"}</version>
        </dependency>
        <#if (configuration.useMybatis!false) != true><!--</#if><dependency>
            <groupId>com.github.myoss</groupId>
            <artifactId>phoenix-mybatis</artifactId>
            <version>${r"${phoenix-mybatis.version}"}</version>
        </dependency><#if (configuration.useMybatis!false) != true>--></#if>
        <!-- myoss cloud dependencies end -->
    </dependencies>

    <dependencyManagement>
        <dependencies>
<#list modules as module>
    <#if module.moduleType != "spring-boot">
            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${module.name}</artifactId>
                <version>${r"${"}${artifactId}.version${r"}"}</version>
            </dependency>
    </#if>
</#list>
        </dependencies>
    </dependencyManagement>
</project>