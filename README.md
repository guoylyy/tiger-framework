# Tiger

该项目是【信贷秘书】后台项目，主要为前端客户端提供稳定的 API 服务。


## 项目环境配置

开发机器上需要准备以下环境：

* gradle 2.4 +
* JDK 1.8 +
* Mysql 5.0.0+
* IDE 可以选用 IDEA 或者 STS
    * 如果选择 STS, 安装 gradle 插件


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

* 代码规范

* 数据库插件如何使用（STS Only)

## Tiger-mng

* tiger-web-mng => src/main/resources/static/ 下应该有一个plugins包，存放所有的js插件资源
  *[plugins] + [后台模板] (teambition>文库>3.后台>后台管理系统>管理系统前端)

### run

1. copy 上述 plugins 文件夹到 src/main/resources/static/ 下
2. run MngMain.java
3. 打开浏览器 [http://localhost:8080/](http://localhost:8080/)

### 自动更新
1. 使用gradle bootRun启动
