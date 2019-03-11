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
      系统
      <small>设置</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/system/edit">系统</a></li>
      <li class="active">设置</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">系统设置</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form" action="/admin/role/edit" method="post">
          <input type="hidden" name="roleId" id="roleId" value="${role.roleId}">
          <div class="form-group">
            <c:forEach items="${systemConfigs}" var="systemConfig" >
            <label>${systemConfig.description}</label>
			<input type="text" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
			<br/>
			</c:forEach>
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(7)").addClass("active");
  		$(".treeview-menu li:eq(0)").addClass("active");
  		
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