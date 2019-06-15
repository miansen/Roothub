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
     节点
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/node/list"> 节点</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">节点列表</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/node/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" name="nodeTitle" value="${nodeTitle}" class="form-control" placeholder="节点名">
            <button type="submit" class="btn btn-primary btn-sm">搜索</button>
          </div>
        </form>
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>名称</th>
            <th>图标</th>
            <th>背景图</th>
            <th>话题数</th>
            <th>描述</th>
            <th>创建时间</th>
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
  	});
  	
  	// 删除节点
  	function actionBtn(id){
  		if(confirm("确定要删除这个节点吗？对应的话题节点也会一起删除")){
  			if(!id){
  				toast("节点ID不能为空", "error");
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