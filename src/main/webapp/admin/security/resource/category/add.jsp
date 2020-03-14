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
    <h1>资源类别<small>添加</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
      <li>安全管理</li>
      <li>资源管理</li>
      <li><a href="${contextPath}/admin/security/resource/category/list">资源类别</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">添加资源类别</h3>
      </div>
      <div class="box-body">
      <c:if test="${not empty error}">
  			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
        <form id="form" action="${contextPath}/admin/resource/category/save" method="post">
          <div class="form-group">
            <label>资源类别名称</label>
            <input type="text" name="resourceCategoryName" class="form-control" placeholder="资源类别名称">
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$("#form").submit(function() {
  			var resourceCategoryName = $("input[name='resourceCategoryName']").val();
  			if (!resourceCategoryName) {
  	  	        bootbox.alert("资源类别名称不能为空");
  	  	        return false;
  	  	      }
 		})
  	});
  </script>
</div>
</common:layout>

