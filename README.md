# Maven Plugin

[![Maven Central](https://img.shields.io/maven-central/v/app.myoss.cloud.maven.plugins/myoss-maven-plugin.svg)](https://maven-badges.herokuapp.com/maven-central/app.myoss.cloud.maven.plugins/myoss-maven-plugin/)
[![GitHub release](https://img.shields.io/github/release/myoss-cloud/myoss-maven-plugin.svg)](https://github.com/myoss-cloud/myoss-maven-plugin/releases)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## Documentation

- [Documentation Home](https://github.com/myoss-cloud/myoss-maven-plugin/wiki)

## Download

- [maven][1]
- [the latest JAR][2]  

[1]: http://repo1.maven.org/maven2/app/myoss/cloud/maven/plugins/myoss-maven-plugin/  
[2]: https://search.maven.org/remote_content?g=app.myoss.cloud.maven.plugins&a=myoss-maven-plugin&v=LATEST

## Maven

```xml
<dependency>
    <groupId>app.myoss.cloud.maven.plugins</groupId>
    <artifactId>myoss-maven-plugin</artifactId>
    <version>2.1.4.RELEASE</version>
</dependency>
```

```xml
<dependency>
    <groupId>app.myoss.cloud.maven.plugins</groupId>
    <artifactId>archetypes-maven-plugin</artifactId>
    <version>2.1.4.RELEASE</version>
</dependency>
```

## archetypes-maven-plugin 生成第一版代码

用于快速生成"项目初始化代码"脚手架

### 生成 SpringBoot 单模块项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:springBootSingleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=spring-boot-single-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo1 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:springBootSingleProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=spring-boot-single-project -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo1 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
```

### 生成 share 项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:shareProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=share-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo2 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:shareProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=share-project -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo2 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
```

### 生成 SpringBoot 多模块项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:springBootMultiModuleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=multi-module-demo3 -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo3 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
-DmodulesJson='[{"name":"order-web","moduleType":"spring-boot"},{"name":"order-service","moduleType":"normal"}]'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:springBootMultiModuleProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=multi-module-demo3 -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo3 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
-DmodulesJson="[{\"name\":\"order-web\",\"moduleType\":\"spring-boot\"},{\"name\":\"order-service\",\"moduleType\":\"normal\"}]"
```


## archetypes-maven-plugin 生成第二版代码

用于快速生成"项目初始化代码"脚手架

### 生成 SpringBoot 单模块项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2SpringBootSingleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=spring-boot-single-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo1 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2SpringBootSingleProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=spring-boot-single-project -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo1 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
```

### 生成 share 项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2ShareProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=share-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo2 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2ShareProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=share-project -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo2 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
```

### 生成 SpringBoot 多模块项目代码

`Mac/Linux` 系统使用示例

```bash
$ mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2SpringBootMultiModuleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/myoss-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=app.myoss.cloud -DartifactId=multi-module-demo3 -Dversion=1.0.0.RELEASE \
-DrootPackageName=app.myoss.cloud.demo3 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
-DmodulesJson='[{"name":"order-web","moduleType":"spring-boot"},{"name":"order-service","moduleType":"normal"}]'
```

`Windows` 系统使用示例

```bash
mvn app.myoss.cloud.maven.plugins:archetypes-maven-plugin:2.1.4.RELEASE:v2SpringBootMultiModuleProject ^
-Ddirectory="C:\Users\jerry\logs\archetypes-maven-plugin" ^
-DgroupId=app.myoss.cloud -DartifactId=multi-module-demo3 -Dversion=1.0.0 ^
-DrootPackageName=app.myoss.cloud.demo3 -Dauthor=Jerry.Chen ^
-DconfigurationJson="{\"useMybatis\": true, \"properties\": {\"自定义属性a\": \"自定义属性a的值\"}}"
-DmodulesJson="[{\"name\":\"order-web\",\"moduleType\":\"spring-boot\"},{\"name\":\"order-service\",\"moduleType\":\"normal\"}]"
```

