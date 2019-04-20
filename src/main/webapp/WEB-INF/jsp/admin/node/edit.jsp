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
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">节点编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form" action="/admin/tag/edit" method="post" enctype="multipart/form-data">
          <input type="hidden" value="${node.nodeId}" name="id">
          <div class="form-group">
            <label>名称</label>
            <input type="text" name="name" value="${node.nodeTitle}" class="form-control">
          </div>
          <div class="form-group">
            <label>图标</label>
            <input type="file" name="file"><br>
            <c:if test="${node.avatarNormal != null}">
              <a href="${node.avatarNormal}" target="_blank"><img src="${node.avatarNormal}" width="50" alt=""></a>
            </c:if>
          </div>
          <div class="form-group">
            <label>背景图</label>
            <input type="file" name="file"><br>
            <c:if test="${node.avatarLarge != null}">
              <a href="${node.avatarLarge}" target="_blank"><img src="${node.avatarLarge}" width="50" alt=""></a>
            </c:if>
          </div>
          <div class="form-group">
            <label for="">描述</label>
            <textarea name="description" rows="7" class="form-control">${node.nodeDesc}</textarea>
          </div>
          <button type="submit" id="btn" class="btn btn-primary">提交</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(4)").addClass("active");
  	});
  	
  	function actionBtn(id, action, self){
  		var msg,url;
  		var tip = $(self).text().replace(/[\r\n]/g, '').trim();
  		if(action === 'delete'){
  			url = '/admin/node/delete?id=' + id;
  	    	msg = '确定要'+tip+'这个节点吗？';
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