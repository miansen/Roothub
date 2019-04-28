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
      <small>添加</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/admin_user/list">用户</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">用户添加</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-6">
            <form id="form" action="/admin/admin_user/add" method="post">
              <div class="form-group">
                <label>用户名</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
              </div>
              <div class="form-group">
                <label>密码</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
              </div>
              <div class="form-group">
                <label>头像</label>
                <p>
          			<button type="button" class="btn btn-primary" id="choiceAvatarBtn">选择头像</button>
         			<button type="button" class="btn btn-success" id="confirmAvatarBtn">确认头像</button>
          			<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          			<input type="hidden" value="" name="adminUserAvatar" id="adminUserAvatar">
        		</p>
        		<div class="row">
          			<div class="col-md-9" id="adjustment">
            			<img src="" id="newAvatarImg" class="hidden origin-avatar" alt="">
          			</div>
          			<div class="col-md-3">
            			<div class="preview"></div>
          			</div>
        		</div>
              </div>
              <div class="form-group">
                <label>角色</label>
                <p>
                	<c:forEach var="role" items="${page.list}">
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
  		$("#form").submit(function() {
  	      var username = $("#username").val();
  	      var password = $("#password").val();
  	      var avatar = $("#adminUserAvatar").val();
  	      var roleIds = [];
  	      $("input[name='roleId']:checked").each(function(index,item){
  	    	roleIds.push($(this).val());
  	      });
  	      if(!username) {
  	        toast('用户名不能为空');
  	        return false;
  	      }
  	      if(!password) {
  	        toast('密码不能为空');
  	        return false;
  	      }
  	      
  	      console.log(username);
  	    console.log(password);
  	  console.log(roleIds);
  	      $.ajax({
  	        url: '/admin/admin_user/add',
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	      	traditional:true, //防止深度序列化,默认false
  	        data: {
  	          username: username,
  	          password: password,
  	          avatar: avatar,
  	          roleIds: roleIds
  	        },
  	        success: function(data) {
  	          if(data.success === true) {
  	            toast('添加成功','success');
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