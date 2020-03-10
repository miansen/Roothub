<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>

<common:adminLayout>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
    <h1>权限<small>添加</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
      <li><a href="${contextPath}/admin/permission/list">权限</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">添加权限</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/permission/save" method="post">
        	<div class="form-group">
            	<label>父权限</label>
            	<div class="input-group">
            	<input type="text" name="parentPermissionVO.permissionId" id="parentPermissionId" class="form-control" style="display: none;">
            	<input type="text" name="parentPermissionVO.permissionName" id="parentPermissionName" class="form-control" readonly="readonly">
            	<span class="input-group-btn">
              	<button type="button" onclick="openDialog(this)" class="btn btn-primary">选择</button>
            	</span>
            </div>
          </div>
          <div class="form-group">
            <label>权限名</label>
            <input type="text" name="permissionName" id="permissionName" class="form-control" placeholder="权限名">
          </div>
          <div class="form-group">
            <label>权限值</label>
            <input type="text" name="permissionValue" id="permissionValue" class="form-control" placeholder="权限值">
          </div>
          <div class="form-group">
            <label>权限描述</label>
            <input type="text" name="permissionDesc" id="permissionDesc" class="form-control" placeholder="权限描述">
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
	//打开父权限页面
	function openDialog() {
	    layui.use('layer', function(){
	        var layer = layui.layer;
	        layer.open({
	            title: ['选择父权限', 'font-size:18px;'],
	            type: 2,
	            area: ['700px', '450px'],
	            fixed: false,
	            maxmin: true,
	            content: "${contextPath}/admin/permission/list/parent"
	        });
	    });
	}
  	$(function(){
  		$("#form").submit(function() {
  	      var permissionName = $("#permissionName").val();
  	      var permissionValue = $("#permissionValue").val();
  	      if (!permissionName) {
  	        toast('权限名不能为空');
  	        return false;
  	      }
  	      if (!permissionValue) {
  	    	 toast('权限值不能为空');
   	        return false;
  	      }
  	    })
  	});
  </script>
</div>
</common:adminLayout>

