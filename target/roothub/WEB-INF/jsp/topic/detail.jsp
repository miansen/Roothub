<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>${topic.title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/resources/wangEditor/wangEditor.min.css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
<script src="/resources/js/logout.js"></script>
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
						<h2 class="topic-detail-title">${topic.title}</h2>
						<p class="gray">
						<!-- 点赞点踩功能，后续在开发吧 -->
							<!-- <i id="up_icon_1"
								class="fa fa-chevron-up
                up-down-disable "
								onclick="voteTopic('UP')"></i> <i id="down_icon_1"
								class="fa fa-chevron-down
                up-down-disable "
								onclick="voteTopic('DOWN')"></i> <span id="up_down_vote_count_1">0</span> -->
							<!-- <span>•</span>  -->
							<c:if test="${topic.top}">
							<span class="label label-primary">置顶</span> <span>•</span>
							</c:if>
							<c:if test="${topic.good}">
							<span class="label label-primary">精华</span> <span>•</span>
							</c:if>
							<span><a href="/user/${topic.author}">${topic.author}</a></span> <span>•</span>
							<span><fmt:formatDate type="both" dateStyle="medium"
									timeStyle="short" value="${topic.createDate}" /></span> <span>•</span>
							<span>${topic.viewCount}次点击</span>
						</p>
					</div>
					<div class="media-right">
						<img
							src="/resources/images/${topic.avatar}"
							class="avatar-lg img-circle">
					</div>
				</div>
			</div>
			<div class="divide"></div>
			<div class="panel-body topic-detail-content show_big_image">
				${topic.content}
				<div>
					<a href="/topic/tag/${topic.tag}"><span class="label label-success">${topic.tag}</span></a>
				</div>
			</div>
			<div class="panel-footer" style="display: none" id="collect">
				<a
					href="javascript:window.open('http://service.weibo.com/share/share.php?url=http://roothub.co//topic/${topic.topicId}?r=${topic.author}&amp;title=${topic.title}', '_blank', 'width=550,height=370'); recordOutboundLink(this, 'Share', 'weibo.com');">分享微博</a>&nbsp;
				<a href="javascript:void(0);" class="collectTopic" onclick="save()"></a> <span
					class="pull-right"><span id="collectCount">${countByTid}</span>个收藏</span>
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
				添加一条新评论 <!-- <a href="javascript:void(0);" id="goTop" class="pull-right" onclick="goTop()">回到顶部</a> -->
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
					<!-- <span class="glyphicon glyphicon-send"></span> --> 评论
				</button>
				<div class="fr">
					<a href="/">← Roothub</a>
				</div>
			</div>
		</div>
	</div>
	
	  <div class="col-md-3 hidden-sm hidden-xs">
  	<div class="panel panel-default" id="session">
  	<c:if test="${user == null}">
      <div class="panel-body" id="nologin">
        <h5>属于技术与资讯的社区</h5><p>在这里你可以学习、分享、提问、回答、诉说，这是一个小众且优雅的社区，欢迎你的加入！</p>
      </div>
      </c:if>
  	 <c:if test="${user != null}">
       <div class="panel-body">
              <div class="media">
                <div class="media-left">
                  <a href="/user/${user.userName}">
                    <img src="/resources/images/${user.avatar}" title="" class="avatar img-circle">
                  </a>
                </div>
                <div class="media-body">
                  <div class="media-heading">
                    <strong><a href="/user/${user.userName}">${user.userName}</a></strong>
                    <div style="color: #7A7A7A; font-size: 12px; margin-top:5px;">
                      <i>${user.signature}</i>
                    </div>
                  </div>
                </div>
                <div style="margin-top: 15px;">
                  <a href="/topic/create"><span class="glyphicon glyphicon-pencil"></span>发布话题</a>
                </div>
              </div>
             <div class="sep10" style="height: 10px;"></div>
	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table_fade" style="font-size: 14px;">
    	<tbody><tr>
        	<td width="33%" align="center"><a href="/user/${user.userName}/topics" class="dark" style="display: block;"><span class="bigger">${countTopicByUserName}</span><div class="sep3"></div><span class="fade">我的主题</span></a></td>
        	<td width="34%" style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);" align="center"><a href="/collect/topics" class="dark" style="display: block;"><span class="bigger">${countCollect}</span><div class="sep3"></div><span class="fade">我的收藏</span></a></td>
        	<td width="33%" align="center"><a href="/" class="dark" style="display: block;"><span class="bigger">2</span><div class="sep3"></div><span class="fade">特别关注</span></a></td>
    	</tr>
	</tbody></table>
            </div>
            <div class="panel-footer" style="background-color: white">
              <div class="row">
                <span class="col-md-6"><a href="/notification/list"><span id="n_count">${notReadNotice}</span> 条未读消息</a></span>
                <span class="col-md-6 text-right">声望：<a href="/top100">0</a></span>
              </div>
            </div>
            </c:if>
    </div>
  </div>
	</div>
	</div>
	</div>
	<div id="back2Top" class="backTop___6Q-ki" style="display:none">
		<div class="line___F1WY0"></div>
		<div class="arrow___3UCwo"></div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="/resources/js/jquery.js"></script>
	<!-- 引入 Bootstrap -->
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/wangEditor/wangEditor.min.js"></script>
	<script src="/resources/js/goTop.js"></script>
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
    'list',  // 列表
    'emoticon',  // 表情
    'image',  // 插入图片
    'table',  // 表格
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
  </script>
</body>
</html>