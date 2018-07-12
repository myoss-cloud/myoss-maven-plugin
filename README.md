# Phoenix Maven Plugin

## archetypes-maven-plugin

用于快速生成"项目初始化代码"脚手架

### 生成 SpringBoot 单模块项目代码

```
mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:springBootSingleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=spring-boot-single-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo1 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

### 生成 share 项目代码

```
mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:shareProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=share-project -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo2 -Dauthor=Jerry.Chen \
-DconfigurationJson='{"useMybatis":true,"properties":{"自定义属性a":"自定义属性a的值"}}'
```

## 生成 SpringBoot 多模块项目代码

```
mvn com.github.myoss.maven.plugins:archetypes-maven-plugin:1.0.0.RELEASE:springBootMultiModuleProject -Ddirectory='/Users/jerry/workspaces/github/myoss/myoss-java/phoenix-maven-plugin/archetypes-maven-plugin/target' \
-DgroupId=com.github.myoss -DartifactId=multi-module-demo3 -Dversion=1.0.0.RELEASE \
-DrootPackageName=com.github.myoss.demo3 -Dauthor=Jerry.Chen \
-DmodulesJson='[{"name":"order-web","moduleType":"spring-boot"},{"name":"order-service","moduleType":"normal"}]'
```
