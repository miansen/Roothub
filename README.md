> 在线地址：[roothub.cn](https://www.roothub.cn)

## 技术栈

- JDK1.8
- SpringMVC
- MySQL
- Mybatis
- Bootstrap
- jQuery
- AJAX
- Redis
- Nginx

## 特性

- 使用SpringMVC开发
- 分层配置Spring xml
- 页面自适应布局
- 使用Redis做缓存
- 使用Mybatis编写SQL
- 事务控制
- 使用logback打印日志

## 功能

- 登录注册
- 发帖
- 回复
- 通知
- 搜索
- 标签
- 个人设置
- 关注
- 收藏
- 分享
- 积分
- 赞、踩

## 快速开始

1. 克隆项目到本地：https://github.com/miansen/Roothub.git
2. 创建MySQL数据库`roothub`，字符集utf8，如果想支持emoji，就要选择utf8mb4字符集。
3. 将项目下的`roothub.sql`文件导入到roothub数据库。
4. 开启Redis，如果Redis是默认配置，则不需要更改配置文件。否则要修改项目下的配置文件`redis.properties`
5. 将项目部署到Tomcat
6. 运行Tomcat

## 反馈

[issues](https://github.com/miansen/Roothub/issues)

## 贡献

欢迎大家提 issues，谢谢！

## License

MIT
