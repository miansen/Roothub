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
      评论
      <small>编辑</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/reply/list">评论</a></li>
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">评论编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="from">
          <div class="form-group" style="margin-bottom: 10px;">
          	<div class="form-group">
            	<div id="editor" style="margin-bottom: 10px;"></div>
          	</div>
            <button type="submit" class="btn btn-primary btn-sm">更新评论</button>
          </div>
        </form>
      </div>
    </div>
  </section>
  <script src="/resources/wangEditor/wangEditor.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(3)").addClass("active");
  		var E = window.wangEditor;
  	    var editor = new E('#editor');
  	    editor.customConfig.uploadFileName = 'file';
  	    editor.customConfig.uploadImgServer = '/common/wangEditorUpload';
  	    editor.customConfig.menus = [
  	    	  'head',  // 标题
  	    	  'bold',  // 粗体
  	    	  'italic',  // 斜体
  	    	  'underline',  // 下划线
  	    	  'strikeThrough',  // 删除线
  	    	  'link',  // 插入链接
  	    	  'list',  // 列表
  	    	  'quote',  // 引用
  	    	  'emoticon',  // 表情
  	    	  'image',  // 插入图片
  	    	  'table',  // 表格
  	    	  'code',  // 插入代码
  	    	  'undo',  // 撤销
  	    	  'redo'  // 重复
  	        ];
  	    editor.create();
  	    editor.txt.html('${fn:replace(reply.replyContent,vEnter,'')}');
		  $("#from").submit(function() {
			if (confirm("确定编辑此评论吗？")) {
				var contentHtml = editor.txt.html();
				$.ajax({
					url: '/admin/reply/edit',
					type: 'post',
					async: false,
					cache: false,
					dataType: 'json',
					data: {
						id: ${reply.replyId},
						content: contentHtml
					},
					success: function(data) {
						if (data.success != null && data.success == true) {
							toast(data.error, "success");
							setTimeout(function() {
								window.location.href = "/admin/reply/list";
							},
							700);
						} else {
							toast(data.error);
						}
					},
					error: function(data) {
						toast(data.error);
						console.log(data.error);
					}
				})
			}
			return false;
		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>