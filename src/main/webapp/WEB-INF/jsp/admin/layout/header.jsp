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
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/skins/_all-skins.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <link rel="stylesheet" href="/resources/css/admin.css">
    <link rel="stylesheet" href="/resources/wangEditor/wangEditor.min.css">
    <link rel="stylesheet" href="/resources/cropper/cropper.css">
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
    <script src="/resources/layui/layui.js"></script>
  	<script src="/resources/layui/layui-paginate.js"></script>
  	<script src="/resources/js/admin/avatar.js"></script>
  	<script src="/resources/cropper/cropper.min.js"></script>

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
  <body class="hold-transition skin-blue sidebar-mini" style="background-color: #ecf0f5;">
    <div>
    <header class="main-header" style="position: fixed; width: 100%">
  <!-- Logo -->
  <a href="/admin/index" class="logo">
    <span class="logo-mini"><b>Roothub</b></span>
    <span class="logo-lg"><b>Roothub</b>后台管理</span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top">
    <!-- Sidebar toggle button-->
    <a href="/admin/index" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
    </a>
    <div class="pull-right" style="line-height: 50px; padding-right: 15px;">
      <a href="/changeLanguage?lang=zh" style="color: #fff;">中文</a>&nbsp;&nbsp;
      <a href="/changeLanguage?lang=en" style="color: #fff;">English</a>
    </div>
  </nav>
</header>
  <%@ include file="side.jsp"%>