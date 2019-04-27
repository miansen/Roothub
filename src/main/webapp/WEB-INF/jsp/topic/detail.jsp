<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<title>${topic.title}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="/resources/wangEditor/wangEditor.min.css">
	<link rel="shortcut icon" href="/resources/images/favicon.ico">
	<link href="/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
	<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-body topic-detail-header">
						<div class="media">
							<div class="media-body">
								<a href="/">主页</a>
								<c:if test="${topic.nodeTitle != null}">
									<span class="chevron">&nbsp;›&nbsp;</span>
									<a href="/n/${topic.nodeTitle}" class="topic-detail-node">${topic.nodeTitle}</a>
								</c:if>
								<div class="sep10"></div>
								<c:choose>
                					<c:when test="${topic.url != null}">
                					<h2><a href="${topic.url}" target="_blank">${topic.title}</a></h2>
                					</c:when>
                				<c:otherwise>
                					<h2>${topic.title}</h2>
                				</c:otherwise>
              				</c:choose>
								<p>
									<div id="topic_${topic.topicId}_votes" class="votes">
										<a href="javascript:" onclick="voteTopic(${topic.topicId},true);"
										class="vote vote_up" title="0 赞同">
										<li class="fa fa-chevron-up"></li>
									</a> <a href="javascript:"
									onclick="voteTopic(${topic.topicId},false);" class="vote vote_down" title="0 反对">
									<li class="fa fa-chevron-down"></li>
								</a>
							</div>
							<span>•</span>
							<c:if test="${topic.top}">
							<span class="label label-primary">置顶</span>
							<span>•</span>
						</c:if>
						<c:if test="${topic.good}">
						<span class="label label-success">精华</span>
						<span>•</span>
					</c:if>
					<span><a href="/user/${topic.author}">${topic.author}</a></span>
					<span>•</span>
					<span><fmt:formatDate type="both" value="${topic.createDate}" /></span>
					<%-- <span>${baseEntity.formatDate(topic.createDate)}</span> --%>
					<%-- <span class="formate-date">${topic.createDate}</span> --%>
					<span>•</span>
					<span>${topic.viewCount}次点击</span>
				</p>
			</div>
			<div class="media-right">
				<img src="${topic.avatar}"
				class="avatar-lg img-circle">
			</div>
		</div>
	</div>
	<div class="divide"></div>
	<div class="panel-body topic-detail-content show_big_image">
		${topic.content}
		<div>
			<a href="/tag/${topic.tag}"><span
				class="label label-success">${topic.tag}</span></a>
			</div>
		</div>
		<div class="panel-footer" style="display: none" id="collect">
			<a
			href="javascript:window.open('http://service.weibo.com/share/share.php?url=https://roothub.cn//topic/${topic.topicId}?r=${topic.author}&amp;title=${topic.title}', '_blank', 'width=550,height=370'); recordOutboundLink(this, 'Share', 'weibo.com');">分享微博</a>&nbsp;
			<a href="javascript:void(0);" class="collectTopic"
			onclick="save()"></a> <span class="pull-right"><span
			id="collectCount">${countByTid}</span>个收藏</span>
		</div>
	</div>
	<c:if test="${topic.replyCount == 0}">
	<div class="panel panel-default">
		<div class="panel-body text-center">目前暂无评论</div>
	</div>
</c:if>
<c:if test="${topic.replyCount > 0}">
<jsp:include page="../reply/replies.jsp"></jsp:include>
</c:if>
<div class="panel panel-default" id="pinglun" style="display: none">
	<div class="panel-heading">
		添加一条新评论
		<!-- <a href="javascript:void(0);" id="goTop" class="pull-right" onclick="goTop()">回到顶部</a> -->
	</div>
	<div class="panel-body">
		<input type="hidden" id="commentId" value="">
		<p class="hidden" id="replyP">
			回复<span id="replyAuthor"></span>: <a
			href="javascript:cancelReply();">取消</a>
		</p>
		<body>
		</body>
		<div id="editor" style="margin-bottom: 10px;"></div>
		<button id="btn" class="btn btn-sm btn-default">
			<!-- <span class="glyphicon glyphicon-send"></span> -->
			评论
		</button>
		<div class="fr">
			<a href="/">← Roothub</a>
		</div>
	</div>
</div>
</div>
<div class="col-md-3 hidden-sm hidden-xs">
	<div class="panel panel-default" id="session"></div>
</div>
</div>
</div>
</div>
<div id="back2Top" class="backTop___6Q-ki" style="display: none">
	<div class="line___F1WY0"></div>
	<div class="arrow___3UCwo"></div>
</div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/wangEditor/wangEditor.min.js"></script>
<script src="/resources/js/goTop.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script src="/resources/js/login_info.js"></script>
<script src="/resources/js/formatDate.js"></script>
<script src="/resources/js/topic/detail.js"></script>
<script src="/resources/js/topic/other-topic.js"></script>
<script type="text/javascript">
	/* 获取登录信息 */
	$.ajax({
		type:"get",
		url:"/session",
		dataType:"json",
		success:function(data){
			if(data.success != null && data.success == true){
				$("#pinglun").show();
				$("#collect").show();
			}
			if(data.success != null && data.success == false){

			}
		},
		error:function(data){

		}
	});

	var E = window.wangEditor;
	var editor = new E('#editor');
	editor.customConfig.debug = true;
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

	  function commentThis(username, commentId) {
	  	$("#replyAuthor").text(username);
	  	$("#commentId").val(commentId);
	  	$("#replyP").removeClass("hidden");
	  }

	  function cancelReply() {
	  	$("#replyAuthor").text("");
	  	$("#commentId").val("");
	  	$("#replyP").addClass("hidden");
	  }
	  /* 回复话题 */
	  $("#btn").click(function () {
	  	var contentHtml = editor.txt.html();
	  	var contentText = editor.txt.text();
	  	var topicId = ${topic.topicId};
	  	if(!contentText) {
	  		alert('请输入回复内容哦');
	  		return false;
	  	} else {
	  		$.ajax({
	  			url: '/reply/save',
	  			type: 'post',
	  			dataType: 'json',
	  			data: {
	  				content: contentText ? contentHtml : '',
	  				topicId: topicId
	  			},
	  			success: function(data){
	  				if(data.success != null && data.success == true) {
	  					window.location.href = "/topic/" + data.data.reply.topicId;
	  				} else {
	  					alert(data.data.error);
	  				}
	  			}
	  		})
	  	}
	  });
	  var tid = ${topic.topicId};
	  $.ajax({
	  	url:"/collect/isCollect",
	  	type:"get",
	  	dataType:"json",
	  	data:{tid:tid},
	  	success:function(data){
	  		if(data.success != null && data.success == true){
	  			$(".collectTopic").text("取消收藏");
	  		}else{
	  			$(".collectTopic").text("加入收藏");
	  		}
	  	},
	  	error:function(data){

	  	}
	  });
	  /* 收藏和取消收藏话题 */
	  function save(){
	  	var collectTopic = $(".collectTopic").text();
        //console.log(collectTopic);
        var url;
        if(collectTopic == "加入收藏"){
        	url = "/collect/save";
        }
        if(collectTopic == "取消收藏"){
        	url = "/collect/delete";
        }
    	//alert("collectTopic："+collectTopic+"  url："+url);
    	$.ajax({
    		url:url,
    		type:"post",
    		dataType:"json",
    		data:{tid:tid},
    		success:function(data){
    			if(data.success != null && data.success == true && data.error == "收藏成功"){
    				//alert(JSON.stringify(data));
    				$(".collectTopic").text("取消收藏");
    			}
    			if(data.success != null && data.success == true && data.error == "取消收藏成功"){
    				//alert(JSON.stringify(data));
    				$(".collectTopic").text("加入收藏");
    			}
    		},
    		error:function(data){
    			
    		}
    	})
    }
    function goTop(){
    	$('body,html').animate({scrollTop:0},500);
    }
    
     var count = ${replyPage.totalRow};//数据总量
	 var limit = ${replyPage.pageSize};//每页显示的条数
	 var url = "/topic/${topic.topicId}?p=";//url
	 function page(){
	 	var page = location.search.match(/p=(\d+)/);  
	 	return page ? page[1] : 1;  
	 }
	 var p = page();//当前页数
	 paginate(count,limit,p,url);

	 var upNumber;
	 var downNumber;
	 function upCount(){
	 	$.ajax({
	 		url:"/topic/vote/count",
	 		type:"get",
	 		dataType:"json",
	 		data:{
	 			tid:tid,
	 			vote:true
	 		},
	 		success:function(data){
	 			upNumber = data.data;
	 			//console.log("赞同=="+upNumber);
	 			if(data.success != null && data.success == true && data.data > 0){
	 				$(".votes .vote_up").html('');
	 				$(".votes .vote_up").append("<li class=\"fa fa-chevron-up\"></li>"+data.data+"");
	 				$(".votes .vote_up").attr("title",data.data+" 赞同");
	 			}
	 		},
	 		error:function(data){

	 		}
	 	});
	 }
	 function downCount(){
	 	$.ajax({
	 		url:"/topic/vote/count",
	 		type:"get",
	 		dataType:"json",
	 		data:{
	 			tid:tid,
	 			vote:false
	 		},
	 		success:function(data){
	 			downNumber = data.data;
	 			//console.log("反对=="+downNumber);
	 			if(data.success != null && data.success == true && data.data > 0){
	 				$(".votes .vote_down").html('');
	 				$(".votes .vote_down").append("<li class=\"fa fa-chevron-down\"></li>"+data.data+"");
	 				$(".votes .vote_down").attr("title",data.data+" 反对");
	 			}
	 		},
	 		error:function(data){

	 		}
	 	});
	 }
	 upCount();
	 downCount();
	 function voteTopic(tid,action){
	 	$.ajax({
	 		url:"/topic/vote",
	 		type:"get",
	 		dataType:"json",
	 		data:{
	 			tid:tid,
	 			vote:action
	 		},
	 		success:function(data){
	 			if(data.success != null && data.success == true){
	 				upCount();
	 				downCount();
	 			}
	 		},
	 		error:function(data){

	 		}
	 	});
	 }
	</script>
</body>
</html>