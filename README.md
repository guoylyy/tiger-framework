# Tiger

该项目是SpringMVC后台项目，主要为前端客户端提供稳定的 API 服务,项目基于多项目依赖构建，适合于横向扩展功能。


## 项目环境配置

开发机器上需要准备以下环境：

* gradle 2.4 + （目前2.4版本应用稳定，其他版本未测试过）
* JDK 1.8 +
* Mysql 5.0.0+
* IDE 可以选用 IDEA 或者 STS（强烈建议使用IDEA)
    * 如果选择 STS, 安装 gradle 插件
  
同时项目使用以下插件：

* flyway （数据迁移）
* checkstyle (代码风格审核）
* junit + mockMVC（组件测试）

导入项目有以下几步：

### 1. 导入 gradle 项目到IDE

### 2. 配置数据库 和 gralde.properties 文件

### 3. 新建数据库，migration 数据

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

待更新
