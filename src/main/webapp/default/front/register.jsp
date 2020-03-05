<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading"><a href="/">首页</a> / 注册</div>
            <div class="panel-body">
            	<c:if test="${not empty error}">
        			<div class="alert alert-danger" role="alert">${error}</div>
      			</c:if>
                <form role="form" id="form" action="${contextPath}/register" method="post">
                    <div class="form-group">
                        <label for="userName">用户名</label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="用户名,只能是2-16位的a-z,A-Z,0-9组合">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="email">邮箱</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="邮箱">
                    </div>
                    <button type="submit" class="btn btn-primary" id="reg_btn">注册</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function () {
    $("#form").on("click", "#reg_btn", function () {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var email = $("#email").val();
        var pattern = /^\w+$/;
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!userName) {
            alert('用户名不能为空哦');
            return false;
        }
        if (!pattern.test(userName)) {
            alert("您的用户名只能为0-9a-zA-Z格式");
            return false;
        }
        if (userName.length < 2) {
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
        if (!myreg.test(email)) {
            alert('邮箱格式不正确');
            return false;
        }
        $("#reg_btn").click();
    });
});
</script>
<%@ include file="layout/footer.jsp" %>