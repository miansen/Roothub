 $(function(){
        $("#zhuceli").addClass("active");
        $("#form").on("click","#reg_btn",function(){
          var username = $("#username").val();
          var password = $("#password").val();
          var email = $("#email").val();
          var pattern=/^\w+$/;
          var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
          if (!username) {
            alert('用户名不能为空哦');
            return false;
          }
          if(!pattern.test(username)){
        	  alert("您的用户名只能为0-9a-zA-Z格式");
              return false;
          }
          if (username.length < 2) {
              alert('用户名的长度不能少于2位');
              return false;
            }
          if (!password) {
            alert('密码不能为空哦');
            return false;
          }
          if (password.length < 6) {
              alert('密码的长度不能少于6位');
              return false;
            }
          if (password.length > 16) {
              alert('密码的长度不能大于16位');
              return false;
            }
          if (!email) {
            alert('邮箱不能为空哦');
            return false;
          }
          if(!myreg.test(email)){
        	  alert('邮箱格式不正确');
              return false;
          }
          $.ajax({
              type:"post",
              url:"/register",
              dataType: "json",
              data: {
              username: $("#username").val(),
              password: $("#password").val(),
              email: $("#email").val(),
            },
            success:function(data){
            	console.log(data);
              if(data.success != null && data.success == false){
                alert(data.error);
                return false;
              }else{
            	  alert('注册成功，点击去登录');
                  location.href = "/login";
              }
            },
            error:function(data){

            }
          });
        });
      });