<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld"%>
<%@ taglib prefix="security" uri="/WEB-INF/tld/security.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/contextPath.jsp"%>

<common:layout>
	<div class="content-wrapper" style="margin: 0px;">
		<section class="content-header">
			<h1>资源类别<small>列表</small></h1>
			<ol class="breadcrumb">
				<li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
				<li>安全管理</li>
      			<li>资源管理</li>
				<li><a href="${contextPath}/admin/security/resource/category/list">资源类别</a></li>
				<li class="active">列表</li>
			</ol>
		</section>
		<section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
					<form action="${contextPath}/admin/security/resource/category/list" method="get" class="form-inline">
	                    <div class="form-group" style="margin-bottom: 10px;">
	                        <input type="text" name="resourceCategoryName" value="${resourceCategoryName}" class="form-control" placeholder="资源类别名称">
	                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
	                    </div>
                	</form>
				</div>
				<div class="box-body" style="padding: 0px;">
                	<c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<security:resourceCategoryList page="${page}" class="table table-bordered table-hover" 
                    		th="资源类别ID;资源类别名称;创建人;创建时间" 
                    		td="resourceCategoryId;resourceCategoryName;userName;createDate" />
							<common:paginate page="${page}" url="${contextPath}/admin/security/resource/category/list" />
                	 	</c:when>
                     	<c:otherwise>
                        	<div class="empty-data">
                        		<img src="${contextPath}/resource/images/no-data.jpg" style="width: 100%;">
                        	</div>
                     	</c:otherwise>
               		</c:choose>
				</div>
			</div>
		</section>
	</div>
</common:layout>