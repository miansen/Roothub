<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>

<common:layout>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
    <h1>菜单<small>添加</small></h1>
    <ol class="breadcrumb">
      <li>首页</li>
      <li>菜单管理</li>
      <li class="active">菜单添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/menu/save" method="post">
        	<div class="form-group">
            	<label>父级菜单</label>
            	<div class="input-group">
            	<input type="text" name="parentMenuVO.menuId" class="form-control" style="display: none;">
            	<input type="text" name="parentMenuVO.menuName" class="form-control" readonly="readonly">
            	<span class="input-group-btn">
              	<button type="button" class="btn btn-primary">选择</button>
            	</span>
            </div>
          </div>
          <div class="form-group">
            <label>菜单的名称</label>
            <input type="text" name="menuName" class="form-control" placeholder="菜单的名称">
          </div>
          <div class="form-group">
            <label>菜单的URL</label>
            <input type="text" name="menuUrl" class="form-control" placeholder="菜单的URL">
          </div>
          <div class="form-group">
            <label>菜单的图标</label>
            <input type="text" name="menuIcon" class="form-control" placeholder="菜单的图标">
          </div>
          <div class="form-group">
            <label>排序</label>
            <input type="text" name="menuSort" class="form-control" placeholder="排序">
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$("#form").submit(function() {
  	      var menuName = $("input[name='menuName']").val();
  	      if (!menuName) {
  	    	bootbox.alert("菜单的名称不能为空");
  	        return false;
  	      }
  	      dialog.loading();
  	    });
  		$("button[type='button']").click(function() {
  			bootbox.dialog({
  			   size: 'xl',
  	  		   message: "<iframe src='${contextPath}/admin/sidebar/list?dialog=true' width='100%' height='100%' frameborder='no' border='0'></iframe>",
  	  		   closeButton: true
  	  		});
  		});
  	});
  </script>
</div>
</common:layout>

