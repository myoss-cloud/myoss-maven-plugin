# Phoenix Maven Plugin

[![Maven Central](https://img.shields.io/maven-central/v/com.github.myoss.maven.plugins/phoenix-maven-plugin.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.myoss/phoenix-maven-plugin/)
[![GitHub release](https://img.shields.io/github/release/myoss-cloud/phoenix-maven-plugin.svg)](https://github.com/myoss-cloud/phoenix-maven-plugin/releases)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## Documentation

- [Documentation Home](https://github.com/myoss-cloud/phoenix-maven-plugin/wiki)

## Download

- [maven][1]
- [the latest JAR][2]  

[1]: http://repo1.maven.org/maven2/com/github/myoss/maven/plugins/phoenix-maven-plugin/  
[2]: https://search.maven.org/remote_content?g=com.github.myoss.maven.plugins&a=phoenix-maven-plugin&v=LATEST

## Maven

```xml
<dependency>
    <groupId>com.github.myoss.maven.plugins</groupId>
    <artifactId>phoenix-maven-plugin</artifactId>
    <version>1.0.1.RELEASE</version>
</dependency>
```

```xml
<dependency>
    <groupId>com.github.myoss.maven.plugins</groupId>
    <artifactId>archetypes-maven-plugin</artifactId>
    <version>1.0.1.RELEASE</version>
</dependency>
```

## archetypes-maven-plugin

用于快速生成"项目初始化代码"脚手架

### 生成 SpringBoot 单模块项目代码

```bash
$ mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:springBootSingleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=spring-boot-single-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo1 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

### 生成 share 项目代码

```bash
$ mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:shareProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=share-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo2 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

### 生成 SpringBoot 多模块项目代码

```bash
$ mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:springBootMultiModuleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=multi-module-demo3 -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo3 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
-DmodulesJson='[{"name":"order-web","moduleType":"spring-boot"},{"name":"order-service","moduleType":"normal"}]'
```
