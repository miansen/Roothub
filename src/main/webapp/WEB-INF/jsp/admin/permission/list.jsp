<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
		<h1>
			权限 <small>列表</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/admin/index"><i class="fa fa-dashboard"></i>
					首页</a></li>
			<li><a href="/admin/permission/list">权限</a></li>
			<li class="active">列表</li>
		</ol>
	</section>
	<section class="content">
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">权限列表</h3>
				<shiro:hasPermission name="permission:add">
					<a class="btn btn-xs btn-primary pull-right"
					   onclick="addParentPermission()" href="javascript:;">添加父权限</a>
				</shiro:hasPermission>
			</div>
			<!-- /.box-header -->
			<div class="box-body" style="padding: 0px;">
				<div class="layui-tab layui-tab-card" lay-filter="permission" style="margin: 0px;">
					<ul class="layui-tab-title">
						<c:forEach items="${permissionMap}" var="permissions" varStatus="permissionsStatus">
							<li id="permission-tab-title-${permissionsStatus.index}">${permissions.key}
								<a href="javascript:if(confirm('删除父权限的同时将会删除子权限，确定要删除吗？')) location.href='/admin/permission/delete?name=${permissions.key}'" style="display: inline">
									<i class="layui-icon layui-icon-close permission-close"></i>
								</a>
							</li>
						</c:forEach>
					</ul>
					<div class="layui-tab-content">
						<c:forEach items="${permissionMap}" var="permissions" varStatus="permissionsStatus">
							<div id= "permission-tab-item-${permissionsStatus.index}" class="layui-tab-item">
								<shiro:hasPermission name="permission:add">
									<a class="btn btn-xs btn-primary pull-right"
									   onclick="addPermission('${permissions.key}')" href="javascript:;">添加子权限</a>
								</shiro:hasPermission>
								<div class="table-responsive">
									<table class="table table-striped table-responsive">
										<thead>
											<tr>
												<th>权限名</th>
												<th>权限值</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${permissions.value}" var="permission">
												<tr>
													<td>${permission.permissionName}</td>
													<td>${permission.permissionValue}</td>
													<td>
														<shiro:hasPermission name="permission:edit">
															<button
																onclick="editPermission(${permission.permissionId}, '${permission.permissionName}', '${permission.permissionValue}', ${permission.pid})"
																class="btn btn-xs btn-warning">编辑
															</button>
														</shiro:hasPermission>
														<shiro:hasPermission name="permission:delete">
															<a href="javascript:if(confirm('确定要删除吗？')) location.href='/admin/permission/delete?id=${permission.permissionId}'" class="btn btn-xs btn-danger">删除</a>
														</shiro:hasPermission></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
      <button class="hidden" id="openModal" data-toggle="modal" data-target="#myModal"></button>
      <!-- Modal -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">添加/编辑权限</h4>
            </div>
            <div class="modal-body">
              <form onsubmit="return;" id="form">
                <input type="hidden" name="id" id="id" value=""/>
                <input type="hidden" name="pid" id="pid" value=""/>
                <input type="hidden" name="pname" id="pname" value=""/>
                <div class="from-group">
                  <label for="name">权限名</label>
                  <input type="text" name="name" id="name" value="" class="form-control" placeholder="如：话题列表"/>
                </div>
                <div class="from-group">
                  <label for="name">权限值</label>
                  <input type="text" name="value" id="value" value="" class="form-control" placeholder="如：topic:list"/>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
              <button type="button" onclick="savePermission()" class="btn btn-primary">保存</button>
            </div>
          </div>
        </div>
      </div>
	</section>
	<script type="text/javascript">
	var url;
	
	// 添加父权限
	function addParentPermission() {
		url = "/admin/permission/add";
		$("#pid").val('');
		$("#pname").val('');
		$("#id").val('');
	    $("#name").val('');
        $("#value").val('');
	    $("#openModal").click();
	    console.log(url);
	}
	
	// 添加子权限
	function addPermission(pname) {
		url = "/admin/permission/add";
		$("#pid").val('');
		$("#pname").val(pname);
		$("#id").val('');
	    $("#name").val('');
        $("#value").val('');
	    $("#openModal").click();
	    console.log(url);
	}
	
	// 编辑子权限
	function editPermission(id, name, value, pid) {
		url = "/admin/permission/edit";
		$("#pid").val(pid);
        $("#pname").val('');
        $("#id").val(id);
        $("#name").val(name);
        $("#value").val(value);
        $("#openModal").click();
    }
	
	// 执行添加或者编辑操作
	function savePermission() {
		  var pid = $("#pid").val();// 父权限ID
		  var pname = $("#pname").val();// 父权限名
		  var id = $("#id").val();// 权限ID
	      var name = $("#name").val();// 权限名
	      var value = $("#value").val();// 权限值
	     
	      if (!name) {
	        toast("请输入权限名");
	        return;
	      }
	      if (!value) {
	        toast("请输入权限值");
	        return;
	      }
	      // var url = "/admin/permission/add";
	      // if (id) url = "/admin/permission/edit";
	      var data = {
	    		  pid: pid,
	    		  pname: pname,
	    		  id: id,
	    		  name: name,
	    		  value: value
	      };
	      console.log(url);
		  console.log(data);
      $.ajax({
  	        url: url,
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	        data: data,
  	        success: function(data) {
  	          if(data.success === true) {
  	            toast(data.error,'success');
  	            setTimeout(function() {
  	            	window.location.reload();
  	            }, 1000);
  	          } else {
  	            toast(data.error,'error');
  	          }
  	        }
  	      })
	    }
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(2)").addClass("active");
  		$(".layui-tab-title li:eq(0)").addClass("layui-this");
  		$(".layui-tab-content div:eq(0)").addClass("layui-show");
  		layui.use('element', function(){
  		  var element = layui.element;
  		  	// console.log(this); //当前Tab标题所在的原始DOM元素
  		  	// console.log(data.index); //得到当前Tab的所在下标
  		  	// console.log(data.elem); //得到当前的Tab大容器
  		  	/* $(".permission-close").click(function(){
  		  		console.log("layui-tab-close click");
  		  		if(confirm('删除父权限的同时将会删除子权限，确定要删除吗？') == true){
  		  			console.log("删除父权限222");
  		  		}
  		  	}); */
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>