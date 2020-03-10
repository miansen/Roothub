<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>
<common:adminLayout>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
    <h1>侧边栏<small>添加</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
      <li><a href="${contextPath}/admin/sidebar/list">侧边栏</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">添加侧边栏</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/sidebar/save" method="post">
        	<div class="form-group">
            	<label>父级侧边栏</label>
            	<div class="input-group">
            	<input type="text" name="parentSidebarVO.sidebarId" id="parentSidebarId" class="form-control" style="display: none;">
            	<input type="text" name="parentSidebarVO.sidebarName" id="parentSidebarName" class="form-control" readonly="readonly">
            	<span class="input-group-btn">
              	<button type="button" class="btn btn-primary">选择</button>
            	</span>
            </div>
          </div>
          <div class="form-group">
            <label>侧边栏的名字</label>
            <input type="text" name="sidebarName" id="sidebarName" class="form-control" placeholder="请输入侧边栏的名字">
          </div>
          <div class="form-group">
            <label>侧边栏的URL</label>
            <input type="text" name="sidebarUrl" id="sidebarUrl" class="form-control" placeholder="点击侧边栏时发送的请求URL">
          </div>
          <div class="form-group">
            <label>排序</label>
            <input type="text" name="sidebarSort" id="sidebarSort" class="form-control" placeholder="排序">
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$("#form").submit(function() {
  	      var sidebarName = $("#sidebarName").val();
  	      if (!sidebarName) {
  	    	bootbox.alert("侧边栏的名字不能为空");
  	        return false;
  	      }
  	    });
  		$("button[type='button']").click(function() {
  			bootbox.dialog({
  	  		   message: "<iframe src='${contextPath}/admin/sidebar/list/parent' width='100%' height='100%' frameborder='no' border='0'></iframe>",
  	  		   closeButton: true
  	  		});
  		});
  	});
  </script>
</div>
</common:adminLayout>

