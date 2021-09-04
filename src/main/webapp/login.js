function smsSend() {
  const mobile = $("#mobile").val();
  const ticket = $("#ticket").val();
  const randStr = $("#randStr").val();

  const data = {
    mobile: mobile,
    type: 1,
    ticket: ticket,
    randStr: randStr
  };

  $.ajax({
    url: "/api/captcha/sms/send",
    type: "post",
    contentType:"application/json;charset=utf-8",
    dataType: "json",
    data: JSON.stringify(data),
    success: function (data) {
      if (data.code === 'SUCCESS') {
        $("#sms-btn").prop('disabled', true);

        let count = 60;
        const countdown = setInterval(countdownFnc, 1000);

        function countdownFnc() {
          $("#verifyTime").val(count);
          $("#sms-btn").text(count + " 秒后重新发送");
          if (count === 0) {
            clearInterval(countdown);
            $("#sms-btn").text("重新发送");
            $("#sms-btn").prop('disabled', false);
          }
          count--;
        }
      }
    },
    error: function (data) {
      console.log("smsSend error");
    }
  });
}

$(function () {
  $("#sms-form").submit(function () {
    const mobile = $("#mobile").val();
    if (!mobile) {
      $(".login-box-msg").text("请输入手机号码");
      return false;
    }
    const code = $("#code").val();
    if (!code) {
      $(".login-box-msg").text("请输入验证码");
      return false;
    }
  });

  $("#sms-btn").click(function () {
    const mobile = $("#mobile").val();
    if (!mobile) {
      $(".login-box-msg").text("请输入手机号码");
      return false;
    }
    var captcha1 = new TencentCaptcha('2004316301', function (res) {
      // 验证失败
      if (res.ret === 1) {
        return;
      }
      // 验证成功
      if (res.ret === 0) {
        // 回调的票据
        $('#ticket').attr('value', res.ticket);
        // 回调的字符串
        $('#randStr').attr('value', res.randstr);
        smsSend();
      }
    });
    captcha1.show();
  });

  $("#mobile").focus(function () {
    $(".login-box-msg").text("");
  });

  $("#code").focus(function () {
    $(".login-box-msg").text("");
  });
});