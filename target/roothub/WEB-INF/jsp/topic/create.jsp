<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>发布话题</title>
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
            <div class="panel-heading">
              <a href="/">主页</a> / 发布话题
            </div>
            <div class="panel-body">
              <form id="form">
                <div class="form-group">
                  <label for="title">标题</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="请输入话题标题，如果标题能够表达完整内容，则正文可以为空">
                </div>
                <div class="form-group">
                  <label for="content">内容</label>
                  <input type="hidden" id="commentId" value="">
                  <p class="hidden" id="replyP">回复<span id="replyAuthor"></span>: <a href="javascript:cancelReply();">取消</a></p>
                  <div id="editor" style="margin-bottom: 10px;"></div>
                </div>
                <div class="form-group">
                  <div class="form-group">
                  <label for="title">标签</label>
                  <input type="text" class="form-control" id="tag" name="title" placeholder="请为你的主题选择一个标签。恰当的归类会让你发布的信息更加有用">
                </div>

                  <style>
                    .tag-logo {
                      width: 16px;
                      height: 16px;
                      border-radius: 2px;
                    }
                    .tag-intro {
                      font-size: 10px;
                      color: gray;
                      word-wrap: break-word;
                      word-break: break-all;
                      text-overflow: ellipsis;
                      overflow:hidden;
                      width: 300px;
                    }
                  </style>

                </div>
                <button type="button" id="btn" class="btn btn-default">
                  <!-- <span class="glyphicon glyphicon-send"></span> --> 发布
                </button>
              </form>
            </div>
          </div>
        </div>
        <div class="col-md-3 hidden-sm hidden-xs">
          <div class="panel panel-default">
            <div class="panel-heading">
              <b>话题发布指南</b>
            </div>
            <div class="panel-body">
              <p>• 在标题中描述内容要点。如果一件事情在标题的长度内就已经可以说清楚，那就没有必要写正文了。</p>
              <p>• 保持对陌生人的友善。用知识去帮助别人。</p>
              <p>• 如果是转载的文章，请务必只填上原文的URL，内容就不用复制过来了。</p>
              <p>• 请为你的主题选择一个或多个标签。恰当的归类会让你发布的信息更加有用。</p>
              <p>• 分享视频请直接复制浏览器容器里的链接粘贴即可（目前支持youtube,优酷）。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="../components/foot.jsp"></jsp:include>
  <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
  <script src="/resources/js/jquery.js"></script>
  <!-- 引入 Bootstrap -->
  <script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/wangEditor/wangEditor.min.js"></script>
  <script type="text/javascript">
    $(function () {
      var E = window.wangEditor;
      var editor = new E('#editor');
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

    $("#btn").click(function () {
      var title = $("#title").val();
      var contentHtml = editor.txt.html();
      var contentText = editor.txt.text();
      var tag = $("#tag").val();
      if(!title) {
        alert('请输入标题哦');
        return false;
    } else if(!tag) {
      alert('请输入标签哦');
      return false;
    }else if(!contentText){
    	alert('请输入正文哦');
    }else {
      $.ajax({
        url: '/topic/save',
        type: 'post',
        async: false,
        cache: false,
        dataType: 'json',
        data: {
          title: title,
          content: contentText ? contentHtml : '',
          tag: tag
        },
        success: function(data){
          //console.log(JSON.stringify(data));
          if(data.success != null && data.success == true) {
            window.location.href = "/topic/" + data.data.topic.topicId;
          } else {
            alert(data.data.error);
          }
        }
      })
    }
  });
  })
</script>
</body>
</html>