<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading"><a href="/">主页</a> / 注册</div>
            <div class="panel-body">
                <form role="form" id="form" method="post">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="用户名,只能是2-16位的a-z,A-Z,0-9组合">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="email">邮箱</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="邮箱">
                    </div>
                    <button type="button" class="btn btn-primary" id="reg_btn">注册</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/default/front/register.js"></script>
<%@ include file="layout/footer.jsp" %>