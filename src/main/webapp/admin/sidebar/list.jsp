<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld"%>
<%@ taglib prefix="sidebar" uri="/WEB-INF/tld/sidebar.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/contextPath.jsp"%>

<common:layout>
	<div class="content-wrapper" style="margin: 0px;">
		<section class="content-header">
			<h1>侧边栏 <small>列表</small></h1>
			<ol class="breadcrumb">
				<li>首页</li>
				<li>侧边栏管理</li>
				<li class="active">侧边栏列表</li>
			</ol>
		</section>
		<section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
					<form action="${contextPath}/admin/sidebar/list?dialog=${param.dialog}" method="get" class="form-inline">
	                    <div class="form-group" style="margin-bottom: 10px;">
	                        <input type="text" name="sidebarName" value="${sidebarName}" class="form-control" placeholder="侧边栏名称">
	                        <button type="submit" class="btn btn-primary">搜索</button>
	                    </div>
                	</form>
				</div>
				<div class="box-body" style="padding: 0px;">
                	<c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<sidebar:list page="${page}" class="table table-bordered table-hover" 
                    		th="侧边栏ID;侧边栏名称;侧边栏URL;父级侧边栏;创建人;创建时间" 
                    		td="sidebarId;sidebarName;sidebarUrl;parentSidebarVO.sidebarName;userName;createDate" />
							<common:paginate page="${page}" url="${contextPath}/admin/sidebar/list?dialog=${param.dialog}&" />
                	 	</c:when>
                     	<c:otherwise>
                        	<div class="empty-data" style="text-align: center;">
                        		<img src="${contextPath}/resources/images/no-data.jpg">
                        	</div>
                     	</c:otherwise>
               		</c:choose>
				</div>
			</div>
		</section>
	</div>
</common:layout>