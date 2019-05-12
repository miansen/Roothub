## 在线地址：[https://www.roothub.cn](https://www.roothub.cn)

登录后台帐号：admin，密码：123

## 技术栈

- JDK1.8
- Spring4.1
- SpringMVC
- Mybatis
- MySQL
- JSP
- Bootstrap
- jQuery
- AJAX
- Redis
- Nginx
- Shiro

## 特性

- 使用 SpringMVC 开发，经典 MVC 三层架构
- 分层配置 Spring xml
- 页面自适应布局
- 使用 Redis 做缓存
- 使用 Mybatis 编写 SQL，自由度高
- 事务控制
- 使用 logback 打印日志
- 使用 Shiro 做权限控制
- 后台管理配置灵活

## 功能

- [x] 登录注册
- [x] 发帖
- [x] 回复
- [x] 通知
- [x] 搜索
- [x] 标签
- [x] 个人设置
- [x] 关注
- [x] 收藏
- [x] 分享
- [x] 积分
- [x] 赞、踩
- [x] 后台管理

## 快速开始

### 准备环境

1. JDK1.8（必须）
2. MySQL5.7.x（必须）
3. Redis3.2.x（非必须）
4. Nginx1.6.x（非必须）

### 开发人员搭建

1. 克隆项目到本地：https://github.com/miansen/Roothub.git
2. 使用你熟悉的开发工具打开，如 IDEA 或者 Eclipse
3. 创建 MySQL 数据库`roothub`，字符集 utf8，如果想支持 emoji，就要选择 utf8mb4 字符集。
4. 将项目下的`src/main/sql/roothub.sql`文件导入到 roothub 数据库。
5. 修改 JDBC 配置文件`src/main/resources/jdbc.properties`，帐号密码换成你自己的
6. 如果你开启了 Redis，就要修改 Redis 的配置文件`src/main/resources/redis.properties`
7. 将项目部署到 Tomcat
8. 运行 Tomcat

到这一步就可以访问了，如果启动后报404，则需要修改项目的访问路径

![](https://i.loli.net/2018/09/14/5b9b13e47b25c.png)

9. Nginx 反代（非必须，不需要可以跳过），下面是 conf 的参考配置，开启 Nginx 后访问：http://localhost

```
server {
	#监听的端口和IP
    listen       80;
    server_name  localhost;

	#这里换成你本地的路径
	root F:/Tomcat/apache-tomcat-8.5.16/wtpwebapps/roothub;

	#动态请求交给Tomcat处理

    location / {

	#这里换成Tomcat的端口

	proxy_pass http://127.0.0.1:8080/;
    }
	
	#所有静态文件由Nginx直接获取
    location ~ .*.(htm|html|gif|jpg|jpeg|png|bmp|swf|ico|rar|zip|txt|flv|mid|doc|ppt|pdf|xls|mp3|wma)$
    {
    	
    }
    location ~ .*.(js|css)$
    {
	   
    } 
        
 }

```

## 反馈

[issues](https://github.com/miansen/Roothub/issues)

## 贡献

欢迎大家提 issues，谢谢！

## License

MIT
