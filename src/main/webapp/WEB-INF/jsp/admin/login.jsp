<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Roothub后台管理系统-登录</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
  <div class="wrapper">
    <div class="row">
      <div class="col-md-9">
        <div class="panel panel-default">
          <div class="panel-heading">
            <a href="/">主页</a> / 登录
          </div>
          <div class="panel-body">
            <form role="form" id="form">
              <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
              </div>
              <div class="form-group">
                <label for="password">密码</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
              </div>
              <button type="button" id="btn" class="btn btn-primary">登录</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>