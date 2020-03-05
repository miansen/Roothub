<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/classes/ui/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>


	<section class="content-header">
    <h1>
      角色
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/role/list">角色</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">角色列表</h3>
        <shiro:hasPermission name="role:add">
          <a href="/admin/role/add" class="btn btn-xs btn-primary pull-right">添加</a>
        </shiro:hasPermission>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>角色ID</th>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>创建日期</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="roleVO" items="${page.list}">
            <tr>
              <td>${roleVO.roleId}</td>
              <td>${roleVO.roleName}</td>
              <td>${roleVO.roleDesc}</td>
              <td>${roleVO.createDate}</td>
              <td>
                <shiro:hasPermission name="role:edit">
                  <a href="/admin/role/edit?id=${role.roleId}" class="btn btn-xs btn-warning">编辑</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="role:delete">
                  <a href="javascript:if(confirm('确定要删除吗？')) location.href='/admin/role/delete?id=${role.roleId}'" class="btn btn-xs btn-danger">删除</a>
                </shiro:hasPermission>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="panel-footer" id="paginate" style="padding: 0px 15px;"></div>
      <common:table page="${page}"  class="table table-bordered" />
      <common:paginate page="${page}" class="panel-footer" style="padding: 0px 18px;" />
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(1)").addClass("active");
  	   
  	  	var count = ${page.totalRow};//数据总量
  	 	var limit = ${page.pageSize};//每页显示的条数
  	 	var pageNumber = ${pageNumber};//当前页数
  	 	var url = "?pageNumber=";//路径
  	 	paginate(count,limit,pageNumber,url);
  	});
  </script>