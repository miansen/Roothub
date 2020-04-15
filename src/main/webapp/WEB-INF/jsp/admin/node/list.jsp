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
     板块
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/node/list">板块</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">板块列表</h3>
      <shiro:hasPermission name="node:add">
          <a href="/admin/node/add" class="btn btn-xs btn-primary">添加</a>
        </shiro:hasPermission>
        </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/node/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" name="nodeTitle" value="${nodeTitle}" class="form-control" placeholder="板块名">
            <button type="submit" class="btn btn-primary btn-sm">搜索</button>
          </div>
        </form>
        <table class="table table-bordered table-hover">
          <thead>
          <tr>
            <th>#</th>
            <th>名称</th>
            <th>图标</th>
            <th>背景图</th>
            <th>帖子数</th>
            <th>描述</th>
            <th>创建时间</th>
            <th>排序</th>
            <th>添加到首页</th>
            <th>添加到导航</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="node" items="${page.list}">
            <tr>
              <td>${node.nodeId}</td>
              <td>${node.nodeTitle}</td>
              <td>
              <c:if test="${node.avatarNormal != null}">
              	<a href="${node.avatarNormal}" target="_blank"><img src="${node.avatarNormal}" width="30" alt=""></a>
              </c:if>
              </td>
              <td>
              <c:if test="${node.avatarLarge != null}">
              	<a href="${node.avatarLarge}" target="_blank"><img src="${node.avatarLarge}" width="30" alt=""></a>
              </c:if>
              </td>
              <td>${node.countTopic}</td>
              <td>${node.nodeDesc}</td>
              <td><fmt:formatDate type="both" value="${node.createDate}" /></td>
              <td>${node.sort}</td>
              <c:choose>
                	<c:when test="${node.addIndex == true}">
                		<td><input name="addIndexStatus" type="checkbox" value="${node.nodeId}" checked="checked"></td>
                	</c:when>
                	<c:otherwise>
                		<td><input name="addIndexStatus" type="checkbox" value="${node.nodeId}"></td>
                	</c:otherwise>
              </c:choose>
              <c:choose>
                	<c:when test="${node.addNav == true}">
                		<td><input name="addNavStatus" type="checkbox" value="${node.nodeId}" checked="checked"></td>
                	</c:when>
                	<c:otherwise>
                		<td><input name="addNavStatus" type="checkbox" value="${node.nodeId}"></td>
                	</c:otherwise>
              </c:choose>
              <td>
                  <shiro:hasPermission name="node:edit">
                  	<a href="/admin/node/edit?id=${node.nodeId}" class="btn btn-xs btn-warning">编辑</a>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="node:delete">
                  	<button onclick="actionBtn('${node.nodeId}')" class="btn btn-xs btn-danger">删除</button>
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
  		$(".sidebar-menu li:eq(4)").addClass("active");
  	  	var nodeTitle = '${nodeTitle}';
  	  	var count = ${page.totalRow};//数据总量
  	 	var limit = ${page.pageSize};//每页显示的条数
  	 	var url = "?nodeTitle="+nodeTitle+"&p=";//url
  	 	var p = ${p};//当前页数
  	 	paginate(count,limit,p,url);
  	 	
  		// 初始化按钮
  	 	$('[name="addIndexStatus"]').bootstrapSwitch({
  	       onColor:"success",
  	       offColor:"danger",
  	       onSwitchChange:function(event,status){
  	    	 var id = $(this).val();
 	    	   $.get("/admin/node/add/index?id=" + id + "&status=" + status, function(data){
				if(data.success === true){
					toast(data.error, "success");
				}else{
					toast(data.error);
				}
			})
  	      }
  	    });
  		
  	 	$('[name="addNavStatus"]').bootstrapSwitch({
  	 	 	onColor:"success",
	        offColor:"danger",
  	 		onSwitchChange:function(event, status){
   	    	   var id = $(this).val();
   	    	   $.get("/admin/node/add/nav?id=" + id + "&status=" + status, function(data){
  				if(data.success === true){
  					toast(data.error, "success");
  				}else{
  					toast(data.error);
  				}
  			})
   	      }
   	    });
  	});
  	
  	// 删除板块
  	function actionBtn(id){
  		if(confirm("确定要删除这个板块吗？对应的话题板块也会一起删除")){
  			if(!id){
  				toast("板块ID不能为空", "error");
  			}
  			$.post("/admin/node/delete",{id: id},function(data){
  				if(data.success === true){
  					toast(data.error, "success");
  					setTimeout(function(){
  						window.location.reload();
  					},700);
  				}else{
  					toast(data.error);
  				}
  			})
  		}
 	}
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>