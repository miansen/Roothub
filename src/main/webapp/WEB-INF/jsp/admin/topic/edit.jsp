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
      <small>编辑</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/topic/list">话题</a></li>
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">话题编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
          	<div class="form-group">
            	<label for="title">标题</label>
            	<input type="text" name="title" id="title" value="${topic.title}" class="form-control" placeholder="标题"/>
          	</div>
          	<div class="form-group">
            	<label for="content">内容</label>
            	<textarea name="content" id="content" class="form-control"
                      	  placeholder="内容，支持Markdown语法">${topic.content}</textarea>
          	</div>
            <div class="form-group">
            	<label for="title">节点</label>
            	<input type="text" name="title" id="title" value="${topic.nodeTitle}" class="form-control" placeholder="节点"/>
          	</div>
            <button type="submit" class="btn btn-primary btn-sm">更新话题</button>
          </div>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(2)").addClass("active");
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>