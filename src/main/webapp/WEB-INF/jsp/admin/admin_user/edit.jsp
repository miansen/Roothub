<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
      用户
      <small>编辑</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/admin_user/list">用户</a></li>
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">用户编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-6">
            <form id="form" action="/admin/admin_user/edit" method="post">
              <input type="hidden" name="id" value="${adminUser.adminUserId}">
              <div class="form-group">
                <label>用户名</label>
                <input type="text" id="username" name="username" value="${adminUser.username}" class="form-control" placeholder="用户名">
              </div>
              <div class="form-group">
                <label>密码</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
              </div>
              <div class="form-group">
                <label>角色</label>
                <p>
                  <c:forEach var="role" items="${roles}">
              			<input type="checkbox" name="roleId" value="${role.roleId}" id="role_${role.roleId}">&nbsp;
                    	<label for="role_${role.roleId}">${role.roleName}</label>
              		</c:forEach>
                </p>
              </div>
              <button type="submit" class="btn btn-xs btn-primary">保存</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(0)").addClass("active");
  		
  		// 被编辑用户ID
  		var adminUserId = ${adminUser.adminUserId}
  		console.log(adminUserId);
  		// 被编辑用户所拥有的角色列表
  		var adminUserRoles = '${adminUserRoles}';
  		// 将数据转换为 JavaScript 对象。
  		var arr = JSON.parse(adminUserRoles);
  		
  		for ( var i = 0; i < arr.length; i++){
  			// 将被编辑用户已有的角色复选框设置为已勾选
  			$("#role_"+arr[i].roleId).attr("checked", true);
  		}
  		
  		$("#form").submit(function() {
  	      var username = $("#username").val();
  	      var password = $("#password").val();
  	      var roleIds = [];
  	      $("input[name='roleId']:checked").each(function(index,item){
  	    	roleIds.push($(this).val());
  	      });
  	      if(!username) {
  	        toast('用户名不能为空');
  	        return false;
  	      }
  	      $.ajax({
  	        url: '/admin/admin_user/edit',
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	      	traditional:true, //防止深度序列化,默认false
  	        data: {
  	          id: adminUserId,
  	          username: username,
  	          password: password,
  	          roleIds: roleIds
  	        },
  	        success: function(data) {
  	          if(data.success === true) {
  	            toast('编辑成功','success');
  	            setTimeout(function() {
  	              window.location.href = '/admin/admin_user/list';
  	            }, 1000);
  	          } else {
  	            toast(data.error,'error');
  	          }
  	        }
  	      })
  	      return false;
  	    })
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>