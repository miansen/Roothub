<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>Roothub后台管理</title>
  <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <!-- 头部 -->
  <jsp:include page="../../admin/layout/header.jsp"></jsp:include>
  <!-- 侧边栏 -->
  <jsp:include page="../../admin/layout/side.jsp"></jsp:include>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">aaa内容主体区域</div>
  </div>
  <!-- 底部 -->
  <jsp:include page="../../admin/layout/footer.jsp"></jsp:include>
</div>
<script src="/resources/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>