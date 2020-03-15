<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>

<common:layout>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
    <h1>侧边栏<small>编辑</small></h1>
    <ol class="breadcrumb">
      <li>首页</li>
      <li>侧边栏管理</li>
      <li class="active">侧边栏编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/sidebar/update" method="post">
        	<div class="form-group">
            	<label>父级侧边栏</label>
            	<div class="input-group">
            	<input type="text" name="parentSidebarVO.sidebarId" id="parentSidebarId" value="${sidebarVO.parentSidebarVO.sidebarId}" class="form-control" style="display: none;">
            	<input type="text" name="parentSidebarVO.sidebarName" id="parentSidebarName" value="${sidebarVO.parentSidebarVO.sidebarName}" class="form-control" readonly="readonly">
            	<span class="input-group-btn">
              	<button type="button" class="btn btn-primary">选择</button>
            	</span>
            </div>
          </div>
          <div class="form-group">
            <label>侧边栏的名称</label>
            <input type="text" name="sidebarId" id="sidebarId" value="${sidebarVO.sidebarId}" class="form-control" style="display: none;">
            <input type="text" name="sidebarName" id="sidebarName" value="${sidebarVO.sidebarName}" class="form-control" placeholder="侧边栏的名称">
          </div>
          <div class="form-group">
            <label>侧边栏的URL</label>
            <input type="text" name="sidebarUrl" id="sidebarUrl" value="${sidebarVO.sidebarUrl}" class="form-control" placeholder="点击侧边栏时发送的请求URL">
          </div>
          <div class="form-group">
            <label>侧边栏的图标</label>
            <input type="text" name="sidebarIcon" id="sidebarUrl" value="${sidebarVO.sidebarIcon}" class="form-control" placeholder="侧边栏的图标">
          </div>
          <div class="form-group">
            <label>排序</label>
            <input type="text" name="sidebarSort" id="sidebarSort" value="${sidebarVO.sidebarSort}" class="form-control" placeholder="排序">
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
  	    	bootbox.alert("侧边栏的名称不能为空");
  	        return false;
  	      }
  	      dialog.loading();
  	    });
  		$("button[type='button']").click(function() {
  			bootbox.dialog({
  	  		   message: "<iframe src='${contextPath}/admin/sidebar/list?dialog=true' width='100%' height='100%' frameborder='no' border='0'></iframe>",
  	  		   closeButton: true
  	  		});
  		});
  	});
  </script>
</div>
</common:layout>

