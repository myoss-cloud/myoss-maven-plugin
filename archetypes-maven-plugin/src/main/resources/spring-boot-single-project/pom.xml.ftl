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
        <phoenix-parent.version>${phoenixParentReleaseVersion}</phoenix-parent.version>
        <phoenix-core.version>${phoenixCoreReleaseVersion}</phoenix-core.version>
        <phoenix-mybatis.version>${phoenixMybatisReleaseVersion}</phoenix-mybatis.version>
    </properties>

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
        </dependencies>
    </dependencyManagement>

    <build>
        <finalName>${r"${project.artifactId}"}</finalName>
        <plugins>
            <plugin>
                <!-- 将spring boot项目进行二次打包，打包成可以运行的jar包 -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>