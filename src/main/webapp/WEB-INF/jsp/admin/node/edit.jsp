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
          <input type="hidden" value="${node.nodeId}" name="nodeId" class="node-id">
          <div class="form-group">
            <label>名称</label>
            <input type="text" name="name" value="${node.nodeTitle}" class="form-control node-name">
          </div>
          <div class="form-group">
            <label>图标</label>
            <input type="hidden" value="${node.avatarNormal}" name="avatarNormal" class="avatarNormal">
            <input type="file" name="file" id="icon-upload"><br>
            <a href="${node.avatarNormal}" target="_blank" id="icon-href"><img src="${node.avatarNormal}" width="50" alt="" id="icon-img"></a>
          </div>
          <div class="form-group">
            <label>背景图</label>
            <input type="hidden" value="${node.avatarLarge}" name="avatarLarge" class="avatarLarge">
            <input type="file" name="file" id="background-upload"><br>
            <a href="${node.avatarLarge}" target="_blank" id="background-href"><img src="${node.avatarLarge}" width="50" alt="" id="background-img"></a>
          </div>
          <div class="form-group">
            <label for="">描述</label>
            <textarea name="description" rows="7" class="form-control node-desc">${node.nodeDesc}</textarea>
          </div>
          <button type="submit" id="btn" class="btn btn-primary">提交</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(4)").addClass("active");
  		layui.use('upload', function () {
  	      var upload = layui.upload;
  	      // 上传图标
  	      upload.render({
	            elem: '#icon-upload', //绑定元素
	            url: '/common/upload?customPath=node', //上传接口
	            done: function (res) {
	            	//请求成功回调
	                console.log(res)
	                console.log(res.data)
	                console.log(res.data[0])
	                $(".avatarNormal").val(res.data[0]);
	                $("#icon-href").attr("href",res.data[0]);
	                $("#icon-img").attr("src",res.data[0]);
	            }, 
	            error: function () {
	                //请求异常回调
	            }
	        });
  	      	// 上传背景图
		    upload.render({
	          elem: '#background-upload', //绑定元素
	          url: '/common/upload?customPath=node', //上传接口
	          done: function (res) {
	          	  //请求成功回调
	              console.log(res)
	              console.log(res.data)
	              console.log(res.data[0])
	              $(".avatarLarge").val(res.data[0]);
	              $("#background-href").attr("href",res.data[0]);
	              $("#background-img").attr("src",res.data[0]);
	          }, 
	          error: function () {
	              //请求异常回调
	          }
	      });
  	    });
  		$("#form").submit(function(){
  			// ID
  			var nodeId = $(".node-id").val();
  			// 名称
  			var nodeTitle = $(".node-name").val();
  			// 图标
  			var avatarNormal = $(".avatarNormal").val();
  			// 背景图
  			var avatarLarge = $(".avatarLarge").val();
  			// 描述
  			var nodeDesc = $(".node-desc").val();
  			var data = {nodeId, nodeTitle, avatarNormal, avatarLarge, nodeDesc};
  			if(confirm("确定要编辑此节点吗？")){
  				if(!nodeId){
  					toast('节点名称不能为空');
  					return false;
  				}
  				console.log(data);
  				$.ajax({
  					url: "/admin/node/edit",
  					type: "post",
  					dataType: "json",
  					data: data,
  					success: function(data){
  						if(data.success == true){
  							toast('编辑成功','success');
  	  						setTimeout(function(){
  	  							window.location.href = "/admin/node/list";
  	  						},1000);
  						}else{
  							toast(data.error,'error');
  						}
  					},
  					error: function(data){
  						
  					}
  				});
  			}
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>