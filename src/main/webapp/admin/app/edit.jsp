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
    <h1>应用<small>基本信息</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/app/index"><i class="fa fa-dashboard"></i>应用首页</a></li>
      <li class="active">基本信息</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">添加应用</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/app/save" method="post">
          <div class="form-group">
            <label>应用名称</label>
            <input type="text" name="appName" class="form-control" placeholder="应用名称" required="required">
          </div>
          <div class="form-group">
            <label>应用描述</label>
            <input type="text" name="appDesc" class="form-control" placeholder="应用描述">
          </div>
          <div class="form-group">
            <label>应用图标</label>
            <input type="text" name="appIcon" class="form-control" placeholder="应用图标">
          </div>
          <div class="form-group">
            <label>应用类别</label>
            <div class="input-group">
            	<input type="text" name="appCategoryId" class="form-control" style="display: none;">
            	<input type="text" name="appCategoryName" class="form-control" readonly="readonly">
            	<span class="input-group-btn">
              	<button type="button" onclick="openIframeDialog('选择应用类别', '${contextPath}/admin/app/category/list?dialog=true')" class="btn btn-primary">选择</button>
            	</span>
            </div>
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$("#form").submit(function() {
  	      var appName = $("input[name='appName']").val();
  	      if (!appName) {
  	        toast('应用名称不能为空');
  	        return false;
  	      }
  	    })
  	});
  </script>
</div>
</common:adminLayout>

