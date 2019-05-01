$(function () {
    var newAvatarImg = $("#newAvatarImg");
    $("#choiceAvatarBtn").click(function () {
      $("#newAvatarFile").click();
    });
    $("#newAvatarFile").change(function () {
      $("#newAvatarImg").attr("src", "");
      var reader = new FileReader();
      reader.onload = function (e) {
    	$("#adjustment").show();
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
          url: '/common/upload/base64',
          async: false,
          cache: false,
          method: 'post',
          dataType: 'json',
          data: {
        	base64: avatarBase64,
            path: "user"
          },
          success: function (data) {
            if(data.success != null && data.success == true) {
            	$("#adminUserAvatar").val(data.error);
            	$("#adjustment").hide();
            } else {
            	alert("上传头像失败");
            }
          }
        })
      }
    })
  })