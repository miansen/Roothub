<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/contextPath.jsp"%>

<common:adminLayout>
	<div class="content-wrapper" style="margin: 0px;">
		<c:if test="${empty param.dialog}">
			<section class="content-header">
				<h1>
					应用类别 <small>列表</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${contextPath}/admin/index"><i
							class="fa fa-dashboard"></i>首页</a></li>
					<li><a href="${contextPath}/admin/app/category/list">应用类别</a></li>
					<li class="active">列表</li>
				</ol>
			</section>
		</c:if>
		<section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">应用类别列表</h3>
				</div>
				<div class="box-body" style="padding: 0px;">
					<form action="${contextPath}/admin/app/category/list" method="get" class="form-inline">
	                    <div class="form-group" style="margin-bottom: 10px;">
	                        <input type="text" name="appCategoryName" value="${appCategoryName}" class="form-control" placeholder="应用类别名称" style="display: inline-block; width: 50%;">
	                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
	                    </div>
                	</form>
                	<c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<app:categoryList page="${page}" class="table table-bordered table-hover" 
                    		th="应用类别名称;应用类别描述" td="appCategoryId;appCategoryName" />
							<common:paginate page="${page}" class="panel-footer" style="padding: 0px 18px;" />
                	 	</c:when>
                     <c:otherwise>
                        <div class="empty-data">
                        	<img src="${contextPath}/admin/resource/images/no-data.jpg" style="width: 100%;">
                        </div>
                     </c:otherwise>
              	  </c:choose>
				</div>
			</div>
		</section>
	</div>
</common:adminLayout>