<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>

<common:adminLayout>
<!-- 内容主体区域 -->
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
        <h3 class="box-title">角色列表</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <common:table page="${page}"  class="table table-bordered table-hover" />
      	<common:paginate page="${page}" url="${contextPath}/admin/role/list" />
      </div>
    </div>
  </section>
  </div>
</common:adminLayout>