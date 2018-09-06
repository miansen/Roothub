<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>修改密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="wrapper">
<jsp:include page="../components/head.jsp"></jsp:include>
  <div class="col-md-3 hidden-sm hidden-xs">
<div class="panel panel-default">
  <div class="list-group">
    <a href="/user/settings/profile" class="list-group-item ">个人设置</a>
    <a href="/user/settings/changeAvatar" class="list-group-item">修改头像</a>
    <a href="/user/settings/changePassword" class="list-group-item active">修改密码</a>
    <!-- <a href="/user/settings/accessToken" class="list-group-item ">用户令牌</a> -->
    <!-- <a href="/user/settings/log" class="list-group-item ">日志记录</a> -->
  </div>
</div>
  </div>

  <div class="col-md-9">
    <div class="panel panel-default">
      <div class="panel-heading">
        <a href="/">主页</a> / 修改密码
      </div>
      <div class="panel-body">
        <form id="form">
          <div class="form-group">
            <label for="oldPassword">旧密码</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword">
          </div>
          <div class="form-group">
            <label for="newPassword">新密码</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword">
          </div>
          <div class="form-group">
            <label for="newPassword2">再次输入新密码</label>
            <input type="password" class="form-control" id="newPassword2" name="newPassword2">
          </div>
            <button type="submit" class="btn btn-primary">修改密码</button>
          <span class="text-danger"></span>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $("#form").submit(function() {
      var oldPassword = $("#oldPassword").val();
      var newPassword = $("#newPassword").val();
      var newPassword2 = $("#newPassword2").val();
      if(!oldPassword) {
    	  alert('请输入旧密码');
          return false;
      }
      if(!newPassword) {
    	  alert('请输入新密码');
          return false;
      }
      if (newPassword.length < 6) {
          alert('密码的长度不能少于6位');
          return false;
        }
      if(newPassword != newPassword2){
    	  alert('2次密码不一致');
          return false;
      }
      $.ajax({
        url: '/user/setting/changePassword',
        type: 'post',
        async: true,
        cache: false,
        dataType: 'json',
        data: {
          oldPassword: oldPassword,
          newPassword: newPassword
        },
        success: function(data) {
          if(data.success != null && data.success == true) {
        	  alert('修改成功，请重新登录');
        	  window.location.href = '/logout';
            /* setTimeout(function() {
              window.location.href = '/logout';
            }, 2000); */
          } else {
        	  alert(data.error);
          }
        }
      });
      return false;
    })
  })
</script>
  </div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script type="text/javascript">
  $(function(){
    $("#shezhili").addClass("active");
  });
</script>
</body></html>
