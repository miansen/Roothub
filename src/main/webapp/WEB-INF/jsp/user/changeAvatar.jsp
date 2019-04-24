<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>修改头像</title>
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
    <a href="/user/settings/changeAvatar" class="list-group-item active">修改头像</a>
    <a href="/user/settings/changePassword" class="list-group-item ">修改密码</a>
    <!-- <a href="/user/settings/accessToken" class="list-group-item ">用户令牌</a> -->
    <!-- <a href="/user/settings/log" class="list-group-item ">日志记录</a> -->
  </div>
</div>
  </div>

  <div class="col-md-9">
    <div class="panel panel-default">
      <div class="panel-heading"><a href="/">主页</a> / 修改头像</div>
      <div class="panel-body">
        <p>
          <button class="btn btn-primary" id="choiceAvatarBtn">选择头像</button>
          <button class="btn btn-success" id="confirmAvatarBtn">确认头像</button>
          <input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
        </p>
        <div class="row">
          <div class="col-md-9">
            <img src="" id="newAvatarImg" class="hidden origin-avatar" alt="">
          </div>
          <div class="col-md-3">
            <div class="preview"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<link rel="stylesheet" href="/resources/cropper/cropper.css">
<script src="/resources/cropper/cropper.min.js"></script>
<script type="text/javascript">
  $(function () {
    var newAvatarImg = $("#newAvatarImg");
    $("#choiceAvatarBtn").click(function () {
      $("#newAvatarFile").click();
    });
    $("#newAvatarFile").change(function () {
      $("#newAvatarImg").attr("src", "");
      var reader = new FileReader();
      reader.onload = function (e) {
        $("#newAvatarImg").attr("src", e.target.result).removeClass('hidden');
      };
      reader.readAsDataURL($(this)[0].files[0]);

      //cropper load image
      setTimeout(function () {
        newAvatarImg.cropper('destroy');
        var $previews = $(".preview");
        newAvatarImg.cropper({
          ready: function (e) {
            var $clone = $(this).clone().removeClass('cropper-hidden');
            $clone.css({
              display: 'block',
              width: '100%',
              minWidth: 0,
              minHeight: 0,
              maxWidth: 'none',
              maxHeight: 'none'
            });
            $previews.css({
              width: '100%',
              overflow: 'hidden'
            }).html($clone);
          },
          viewMode: 1,
          aspectRatio: 1,
          scalable: false,
          cropBoxResizable: false,
          crop: function (e) {
            var imageData = $(this).cropper('getImageData');
            var previewAspectRatio = e.width / e.height;
            $previews.each(function () {
              var $preview = $(this);
              var previewWidth = $preview.width();
              var previewHeight = previewWidth / previewAspectRatio;
              var imageScaledRatio = e.width / previewWidth;
              $preview.height(previewHeight).find('img').css({
                width: imageData.naturalWidth / imageScaledRatio,
                height: imageData.naturalHeight / imageScaledRatio,
                marginLeft: -e.x / imageScaledRatio,
                marginTop: -e.y / imageScaledRatio
              });
            });
          }
        });
      }, 200);
    });
    $("#confirmAvatarBtn").click(function() {
      if(!$("#newAvatarFile").val()) {
        alert("请先选择图片");
      } else {
        var avatarBase64 = newAvatarImg.cropper('getCroppedCanvas', {width: 100, height: 100}).toDataURL();
        $.ajax({
          url: '/user/setting/changeAvatar',
          async: false,
          cache: false,
          method: 'post',
          dataType: 'json',
          data: {
        	avatarBase64: avatarBase64,
            path: "user"
          },
          success: function (data) {
            if(data.success != null && data.success == true) {
            	alert("修改头像成功");
            	location.reload();
            } else {
            	alert("修改头像失败");
            }
          }
        })
      }
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