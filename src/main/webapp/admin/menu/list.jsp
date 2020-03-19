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
			<h1>菜单<small>列表</small></h1>
			<ol class="breadcrumb">
				<li>首页</li>
				<li>菜单管理</li>
				<li class="active">菜单列表</li>
			</ol>
		</section>
		<section class="content">
			<div class="box box-info">
				<div class="box-header with-border">
					<form action="${contextPath}/admin/menu/list?dialog=${param.dialog}" method="get" class="form-inline">
	                    <div class="form-group" style="margin-bottom: 10px;">
	                        <input type="text" name="menuName" value="${menuName}" class="form-control" placeholder="菜单名称">
	                        <button type="submit" class="btn btn-primary">搜索</button>
	                    </div>
                	</form>
				</div>
				<div class="box-body" style="padding: 0px;">
                	<c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<sidebar:list page="${page}" class="table table-bordered table-hover" 
                    		th="菜单ID;菜单名称;菜单URL;父级菜单;创建人;创建时间" 
                    		td="menuId;menuName;menuUrl;parentMenuVO.menuName;userName;createDate" checkbox="true"/>
							<common:paginate page="${page}" url="${contextPath}/admin/menu/list?dialog=${param.dialog}&" />
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