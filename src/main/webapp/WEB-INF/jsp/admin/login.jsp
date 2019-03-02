<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!doctype html>
  <html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Roothub后台管理系统</title>
    <link rel="icon" href="/resources/images/favicon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/skins/_all-skins.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <style>
      @media screen and (max-width: 768px) {
        .content-header {
          position: relative;
          padding: 65px 15px 0 15px;
        }
      }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
    <script src="/resources/admin/js/app.min.js"></script>

    <script>
      function toast(txt, icon) {
        $.toast({
          text: txt, // Text that is to be shown in the toast
          heading: '系统提醒', // Optional heading to be shown on the toast
          icon: icon || 'error', // Type of toast icon warning, info, success, error
          showHideTransition: 'slide', // fade, slide or plain
          allowToastClose: true, // Boolean value true or false
          hideAfter: 2000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
          stack: false, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
          position: 'top-right', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        });
      }
    </script>
  </head>
<body class="hold-transition login-page">
  <div class="login-box">
    <div class="login-logo">
      <a href="javascript:;"><b>Roothub</b>管理平台</a>
    </div>
    <div class="login-box-body">
      <p class="login-box-msg">Roothub 管理平台登录</p>
      <c:if test="${error != null}">
        <div class="text-red">${error}</div>
      </c:if>
      <form id="form" action="/admin/login" method="post">
        <div class="form-group has-feedback">
          <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
          <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <input type="password" class="form-control" id="password" name="password" placeholder="密码">
          <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <!-- <div class="form-group">
          <div class="input-group">
            <input type="text" class="form-control" id="code" name="code" placeholder="验证码"/>
            <span class="input-group-btn">
            <img style="border: 1px solid #ccc;" src="/common/captcha" id="changeCode"/>
          </span>
          </div>
        </div> -->
        <div class="row">
          <div class="col-xs-8">
            <input type="checkbox" name="rememberMe" checked id="rememberMe" value="1"> <label for="rememberMe">记住我</label>
          </div>
          <div class="col-xs-4">
            <button type="submit" class="btn btn-primary btn-block btn-flat"><i class="fa fa-adminUser"></i> 登录</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  </body>
</html>