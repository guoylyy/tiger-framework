# tiger-framework

本项目是 SpringMVC 后台项目，主要为前端客户端提供稳定的 API 服务,项目基于多项目依赖构建，适合于横向扩展功能。

项目基于Java，愿景是实现一套那来就用的 Java 小企业开放框架，免去技术人员选择和搭建 Java Web 项目的烦恼。

## 项目环境配置

开发机器上需要准备以下环境：

* gradle 2.4 +
* JDK 1.8 +
* Mysql 5.5.0+
* IDEA 


## 开发环境配置

### 1. 导入 gradle 项目到IDEA

### 2. 配置数据库 mysql.properties 和 gralde.properties 文件
* mysql.properties // 放在项目dal目录的resource文件夹中

```
# c3p0.X
c3p0.driverClassName=com.mysql.jdbc.Driver
c3p0.url=jdbc:mysql://localhost:3306/nevermore_dev?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8  //改我
c3p0.username=xxx  //改我
c3p0.password=xxx  //改我
c3p0.MaxPoolSize=20
c3p0.MinPoolSize=10


```

* gradle.properties //放在项目根目录下

```
flyway.user=xx  //改我
flyway.password=xx  //改我
flyway.url=jdbc:mysql://localhost:3306
flyway.schemas=nevermore_dev


```



### 3. 新建数据库，migration 数据

```
	gradle flywayMigrate -i //项目根目录执行
```

### 4. run APIMain.java

当你看到 console 报一下内容时候，项目就已经跑起来了：


```
 Http11NioProtocol: Initializing ProtocolHandler ["http-nio-8080"]
 Http11NioProtocol: Starting ProtocolHandler ["http-nio-8080"]
 NioSelectorPool: Using a shared selector for servlet write/read
 TomcatEmbeddedServletContainer: Tomcat started on port(s): 8080 (http)
 APIMain: Started APIMain in 28.597 seconds (JVM running for 29.326)
```

## 开发注意事项

* [代码规范](https://github.com/404Design/404-blog/blob/master/rules/java-code-style.md)
* [用RAML表达 API 需求](http://blog.guoyiliang.com/2015/04/23/raml-init/)
* 开放封闭原则（尽量扩展，而不是修改）


## 计划

目前计划是：

* 加入基于redis的缓存机制
* 加入基于角色权限的灵活配置策略

