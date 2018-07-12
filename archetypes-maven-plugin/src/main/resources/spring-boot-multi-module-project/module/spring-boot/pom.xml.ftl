<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>${groupId}</groupId>
        <artifactId>${artifactId}</artifactId>
        <version>${version}</version>
    </parent>

    <artifactId>${moduleArtifactId}</artifactId>
    <name>${moduleArtifactId}</name>

    <properties>
    </properties>

    <dependencies>
<#list modules as module>
    <#if module.moduleType != "spring-boot">
        <dependency>
            <groupId>${groupId}</groupId>
            <artifactId>${module.name}</artifactId>
        </dependency>
    </#if>
</#list>
    </dependencies>

    <build>
        <finalName>${artifactId}</finalName>
        <plugins>
            <plugin>
                <!-- 将spring boot项目进行二次打包，打包成可以运行的jar包 -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>