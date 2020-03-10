<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/contextPath.jsp" %>
<common:adminLayout>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
		<h1>应用 <small>列表</small></h1>
		<ol class="breadcrumb">
			<li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i>首页</a></li>
			<li><a href="${contextPath}/admin/app/list">应用</a></li>
			<li class="active">列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="box box-info">
			<div class="box-body" style="padding: 0px;">
				<form action="${contextPath}/admin/app/list" method="get" class="form-inline">
                    <div class="form-group" style="margin-bottom: 10px;">
                        <input type="text" name="appName" value="${appName}" class="form-control" placeholder="应用名称" style="display: inline-block; width: 50%;">
                        <input type="text" name="appCategoryName" value="${appCategoryName}" class="form-control" placeholder="应用分类名称" style="display: inline-block; width: 50%;">
                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
                    </div>
               	</form>
               	<c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<div class="row">
                    			<c:forEach var="appVO" items="${page.list}">
                    				<div class="col-lg-3 col-xs-6">
                    					<div class="small-box bg-aqua">
                    						<div class="inner"><h3>${appVO.appName}</h3><p>${appVO.appDesc}</p></div>
											<div class="icon"><i class="ion ion-ios-list-outline"></i></div>
											<a href="/admin/app/index" class="small-box-footer">更多 <i class="fa fa-arrow-circle-right"></i></a>
                    					</div>
                    				</div>
                    			</c:forEach>
                    		</div>
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