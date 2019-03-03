<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
     用户
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/admin_user/list">用户</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">用户列表</h3>
        <shiro:hasPermission name="admin_user:add">
          <a href="/admin/admin_user/add" class="btn btn-xs btn-primary pull-right">添加</a>
        </shiro:hasPermission>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>用户名</th>
            <th>角色</th>
            <th>注册时间</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="adminUser" items="${page.list}">
            <tr>
              <td>${adminUser.adminUserId}</td>
              <td>${adminUser.username}</td>
              <td>
              	<c:forEach var="role" items="${adminUser.roles}">
              		${role.roleName}
              	</c:forEach>
              </td>
              <td><fmt:formatDate type="both" value="${adminUser.createDate}" /></td>
              <td>
               	<shiro:hasPermission name="admin_user:edit">
                  <a href="/admin/admin_user/edit?id=${adminUser.adminUserId}" class="btn btn-xs btn-warning">编辑</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="admin_user:delete">
                  <a href="javascript:if(confirm('确定要删除吗？')) location.href='/admin/admin_user/delete?id=${adminUser.adminUserId}'" class="btn btn-xs btn-danger">删除</a>
                </shiro:hasPermission>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="panel-footer" id="paginate"></div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(0)").addClass("active");
  	   
  	  	var count = ${page.totalRow};//数据总量
  	 	var limit = ${page.pageSize};//每页显示的条数
  	 	var p = ${p};//当前页数
  	 	var url = "?p=";//路径
  	 	paginate(count,limit,p,url);
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>