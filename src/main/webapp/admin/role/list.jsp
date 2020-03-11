<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ taglib prefix="role" uri="/WEB-INF/tld/role.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>

<common:layout>
<div class="content-wrapper" style="margin: 0px;">
	<section class="content-header">
    <h1>角色<small>列表</small></h1>
    <ol class="breadcrumb">
      <li><a href="${contextPath}/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="${contextPath}/admin/role/list">角色</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <form action="${contextPath}/admin/role/list" method="get" class="form-inline">
            <div class="form-group" style="margin-bottom: 10px;">
                <input type="text" name="roleName" value="${roleName}" class="form-control" placeholder="角色名">
                <button type="submit" class="btn btn-primary btn-sm">搜索</button>
            </div>
        </form>
      </div>
      <div class="box-body" style="padding: 0px;">
        <c:choose>
        	<c:when test="${page != null && fn:length(page.list) > 0}">
            	<role:list page="${page}" class="table table-bordered table-hover" 
              		th="角色ID;角色名;角色描述;创建时间" 
              		td="roleId;roleName;roleDesc;createDate" />
				<common:paginate page="${page}" url="${contextPath}/admin/role/list" />
        	 </c:when>
             <c:otherwise>
                <div class="panel panel-default">
					<div class="panel-body">
						<h1>: (</h1>
      					<p>啥都没有</p>
					</div>
				</div>
             </c:otherwise>
         </c:choose>
      </div>
    </div>
  </section>
  </div>
</common:layout>