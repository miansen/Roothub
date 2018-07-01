<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>修改个人资料</title>
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
    <a href="/user/settings/profile" class="list-group-item active">个人设置</a>
    <a href="/user/settings/changeAvatar" class="list-group-item ">修改头像</a>
    <a href="/user/settings/changePassword" class="list-group-item ">修改密码</a>
    <!-- <a href="/user/settings/log" class="list-group-item ">日志记录</a> -->
  </div>
</div>
  </div>

  <div class="col-md-9">
    <div class="panel panel-default">
      <div class="panel-heading">
        <a href="/">主页</a> / 个人设置
      </div>
      <div class="panel-body">
        <form action="/user/setting/profile" method="post" id="userProfileForm" enctype="multipart/form-data">
          <div class="form-group">
            <label for="username">昵称</label>
            <input type="text" disabled="" class="form-control" id="username" value="${user.userName}">
          </div>
          <div class="form-group">
            <label for="email">邮箱</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}">
          </div>
          <div class="form-group">
            <label for="url">个人网站</label>
            <input type="text" class="form-control" id="url" name="url" value="${user.url}">
          </div>
          <div class="form-group">
            <label for="thirdId">github</label>
            <input type="text" class="form-control" id="thirdId" name="thirdId" value="${user.thirdId}">
          </div>
          <div class="form-group">
            <label for="userAddr">所在地</label>
            <input type="text" class="form-control" id="userAddr" name="userAddr" value="${user.userAddr}">
          </div>
          <div class="form-group">
            <label for="signature">个性签名</label>
            <textarea class="form-control" name="signature" id="signature">${user.signature}</textarea>
          </div>
          <!-- <div class="form-group">
            <input type="checkbox" name="commentEmail" id="commentEmail" checked="">
            <label for="commentEmail">话题被评论邮件提醒</label>
          </div>
          <div class="form-group">
            <input type="checkbox" name="replyEmail" id="replyEmail" checked="">
            <label for="replyEmail">评论被回复邮件提醒</label>
          </div> -->
            <button type="button" id="userProfileUpdateBtn" onclick="updateUserProfile()" class="btn btn-primary">保存设置
            </button>
          <span id="error_message"></span>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  function updateUserProfile() {
    var errors = 0;
    var em = $("#error_message");
    var signature = $("#signature").val();
    var email = $("#email").val();
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (signature.length > 20) {
      errors++;
      em.html("个性签名不能超过20个字");
    }
    if(!myreg.test(email)){
    	errors++;
    	em.html("邮箱格式不正确");
    }
    if (errors === 0) {
      var form = $("#userProfileForm");
      form.submit();
    }
  }
</script>
  </div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script type="text/javascript">
  $(function(){
    $("#shezhili").addClass("active");
  });
</script>
</body>
</html>