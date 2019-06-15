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
      话题
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/topic/list">话题</a></li>
      <li class="active">列表</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">话题列表</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/topic/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" readonly id="startDate" name="startDate" value="${startDate}"
                   class="form-control" placeholder="开始时间">
            <input type="text" readonly id="endDate" name="endDate" value="${endDate}"
                   class="form-control" placeholder="结束时间">
            <input type="text" name="author" value="${author}" class="form-control" placeholder="用户名">
            <button type="submit" class="btn btn-primary btn-sm">搜索</button>
          </div>
        </form>
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>标题</th>
            <th>用户</th>
            <th>状态</th>
            <th>节点</th>
            <th>时间</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="topic" items="${page.list}">
            <tr>
              <td>${topic.topicId}</td>
              <td><a href="/topic/${topic.topicId}" target="_blank">${topic.title}</a></td>
              <td><a href="/user/${topic.author}" target="_blank">${topic.author}</a></td>
              <td>
              <c:if test="${topic.top}">置顶</c:if>
              <c:if test="${topic.good}">精华</c:if>
                  &nbsp;
              </td>
              <td>${topic.nodeTitle}</td>
              <td><fmt:formatDate type="both" value="${topic.createDate}" /></td>
              <td>
                  <shiro:hasPermission name="topic:top">
                  	<button onclick="actionBtn('${topic.topicId}', 'top', this)" class="btn btn-xs btn-primary">
                  		<c:choose>
                  			<c:when test="${topic.top}">取消置顶</c:when>
                  			<c:otherwise>置顶</c:otherwise>
                  		</c:choose>
                  	</button>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="topic:good">
                  	<button onclick="actionBtn('${topic.topicId}', 'good', this)" class="btn btn-xs btn-primary">
                  		<c:choose>
                  			<c:when test="${topic.good}"> 取消加精</c:when>
                  			<c:otherwise> 加精</c:otherwise>
                  		</c:choose>
                  	</button>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="topic:edit">
                  	<a href="/admin/topic/edit?id=${topic.topicId}" class="btn btn-xs btn-warning">编辑</a>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="topic:delete">
                  	<button onclick="actionBtn('${topic.topicId}', 'delete', this)" class="btn btn-xs btn-danger">删除</button>
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
  		$(".sidebar-menu li:eq(2)").addClass("active");
  		$("#startDate").datepicker({
  	      autoclose: true,
  	      format: 'yyyy-mm-dd',
  	      todayBtn: true,
  	      todayHighlight: true,
  	    });
  	    $("#endDate").datepicker({
  	      autoclose: true,
  	      format: 'yyyy-mm-dd',
  	      todayBtn: true,
  	      todayHighlight: true,
  	    });
  	   
  	    var startDate = '${startDate}';
  	    var endDate = '${endDate}';
  	    var author = '${author}';
  	  	var count = ${page.totalRow};//数据总量
  	 	var limit = ${page.pageSize};//每页显示的条数
  	 	var url = "?startDate="+startDate+"&endDate="+endDate+"&author="+author+"&p=";//url
  	 	function page(){
  	     	var page = location.search.match(/p=(\d+)/);  
  	     	return page ? page[1] : 1;  
  	 	}
  	 	var p = page();//当前页数
  	 	paginate(count,limit,p,url);
  	});
  	
  	function actionBtn(id, action, self){
  		var msg,url;
  		var tip = $(self).text().replace(/[\r\n]/g, '').trim();
  		if(action === 'top'){
  			url = '/admin/topic/top?id=' + id;
  			msg = '确定' + tip + '这条话题吗？';
  		}else if(action === 'good'){
  			url = '/admin/topic/good?id=' + id;
  	        msg = '确定'+tip+'这条话题吗？';
  		}else if(action === 'delete'){
  			url = '/admin/topic/delete?id=' + id;
  	        msg = '确定要删除这条话题吗？';
  		}
  		if(confirm(msg)){
  			$.get(url,function(data){
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