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
    <h1>应用类别<small>添加</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
      <li><a href="${contextPath}/admin/app/category/list">应用类别</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">添加应用类别</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/app/category/save" method="post">
          <div class="form-group">
            <label>应用类别名称</label>
            <input type="text" name="appCategoryName" id="appCategoryName" class="form-control" placeholder="应用类别名称">
          </div>
          <div class="form-group">
            <label>应用类别描述</label>
            <input type="text" name="appCategoryDesc" id="appCategoryDesc" class="form-control" placeholder="应用类别描述">
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$("#form").submit(function() {
  	      var appCategoryName = $("#appCategoryName").val();
  	      if (!appCategoryName) {
  	        toast('应用类别名称不能为空');
  	        return false;
  	      }
  	    })
  	});
  </script>
</div>
</common:adminLayout>

