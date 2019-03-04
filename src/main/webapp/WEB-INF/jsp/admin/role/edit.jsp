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
      角色
      <small>编辑</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/role/list">角色</a></li>
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">角色编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form" action="/admin/role/edit" method="post">
          <input type="hidden" name="roleId" id="roleId" value="${role.roleId}">
          <div class="form-group">
            <label>角色名</label>
            <input type="text" name="roleName" id="roleName" value="${role.roleName}" class="form-control" placeholder="角色名">
          </div>
          <div class="form-group">
            <c:forEach items="${permissionMap}" var="permissions" > 
            	<label for="">${permissions.key}</label>
            	<p>
					<c:forEach items="${permissions.value}" var="permission" > 
						<input type="checkbox" name="permissionIds" id="permission_${permission.permissionId}" 
						value="${permission.permissionId}"/>${permission.permissionName}&nbsp;&nbsp;
					</c:forEach>
				</p>
				<br></br>
			</c:forEach> 
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(1)").addClass("active");
  		
  		// 被编辑角色ID
  		var roleId = ${role.roleId}
  		console.log(roleId);
  		// 被编辑角色所拥有的权限列表
  		var roleHaspermissions = '${roleHaspermissions}';
  		console.log(roleHaspermissions);
  		// 将数据转换为 JavaScript 对象。
  		var arr = JSON.parse(roleHaspermissions);
  		console.log(arr);
  		for ( var i = 0; i < arr.length; i++){
  			// 将被编辑角色已有的权限复选框设置为已勾选
  			$("#permission_"+arr[i].permissionId).attr("checked", true);
  		}
  		
  		$("#form").submit(function() {
  	      var roleName = $("#roleName").val();
  	      var permissionIds = [];
  	      $("input[name='permissionIds']:checked").each(function(index,item){
  	    	permissionIds.push($(this).val());
  	      });
  	      if(!roleName) {
  	        toast('角色名不能为空');
  	        return false;
  	      }
  	      $.ajax({
  	        url: '/admin/role/edit',
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	      	traditional:true, //防止深度序列化,默认false
  	        data: {
  	          roleId: roleId,
  	          roleName: roleName,
  	          permissionIds: permissionIds
  	        },
  	        success: function(data) {
  	          if(data.success === true) {
  	            toast('编辑成功','success');
  	            setTimeout(function() {
  	            	window.location.reload();
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