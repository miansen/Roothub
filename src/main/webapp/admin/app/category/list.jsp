<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="common" uri="/WEB-INF/classes/ui/common.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/classes/ui/app.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/contextPath.jsp"%>

<common:adminLayout>
	<div class="content-wrapper" style="margin: 0px;">
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
		<section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">应用类别列表</h3>
				</div>
				<div class="box-body" style="padding: 0px;">
					<app:categoryList page="${page}" class="table table-bordered table-hover"
						th="类别名称;类别描述"
						td="appCategoryId;appCategoryName" />
					<common:paginate page="${page}" class="panel-footer" style="padding: 0px 18px;" />
				</div>
			</div>
		</section>
	</div>
</common:adminLayout>