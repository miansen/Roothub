<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./contextPath.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Roothub-登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${contextPath}/resources/libs/bootstrap-v3.4.1/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${contextPath}/resources/libs/font-awesome-v4.7.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${contextPath}/resources/libs/Ionicons-v2.0.0/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${contextPath}/resources/libs/AdminLTE-v2.4.18/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${contextPath}/resources/libs/AdminLTE-v2.4.18/dist/css/skins/skin-blue.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <b>Roothub</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">

        <p class="login-box-msg text-red">${errorMsg}</p>

        <form id="sms-form" action="/sms/login" method="post">
            <div class="form-group has-feedback">
                <input type="text" class="form-control required" id="mobile" name="mobile" placeholder="手机号码">
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control required" id="code" name="code" placeholder="验证码"/>
                    <input type="hidden" value="" name="ticket" id="ticket">
                    <input type="hidden" value="" name="randStr" id="randStr">
                    <input type="hidden" value="" name="verifyTime" id="verifyTime"/>
                    <span class="input-group-btn">
              <button id="sms-btn" type="button" class="btn btn-primary">获取验证码</button>
            </span>
                </div>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" id="smsBtn">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="${contextPath}/resources/libs/jquery-v3.4.1/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${contextPath}/resources/libs/bootstrap-v3.4.1/dist/js/bootstrap.min.js"></script>
<script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
<script src="${contextPath}/login.js"></script>
</body>
</html>