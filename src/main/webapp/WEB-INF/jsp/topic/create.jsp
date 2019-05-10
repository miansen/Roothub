<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>发布话题</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
              <%-- <div class="form-group">
                <label for="tab">板块</label>
                <select id="tab" class="form-control" name="tab" onchange="getNode()">
                 <c:forEach var="item" items="${tabList}" varStatus="status">
                 <c:if test="${item.tabName != 'all' && item.tabName != 'member'}">
                 <option value="${item.tabName}">${item.tabDesc}</option>
               </c:if>
             </c:forEach>
           </select>
         </div> --%>
         <c:if test="${fn:length(node) == 0}">
         	<div class="form-group">
          <label for="node">节点</label>
          <select id="node" class="form-control" name="node">
          	<c:forEach var="item" items="${nodeList}" varStatus="status">
                 <option value="${item.nodeTitle}">${item.nodeTitle}</option>
             </c:forEach>
          </select>
        </div>
        </c:if>
        <div class="form-group">
          <div class="form-group">
            <label for="title">标签</label>
            <input type="text" class="form-control" id="tag" name="title" placeholder="请为你的主题选择一个标签。恰当的归类会让你发布的信息更加有用">
          </div>
        </div>
        <button type="button" id="btn" class="btn btn-default">发布</button>
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
      <p>• 请为你的主题选择一个节点。恰当的归类会让你发布的信息更加有用。</p>
    </div>
  </div>
</div>
</div>
</div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/wangEditor/wangEditor.min.js"></script>
<!-- <script src="/resources/js/topic/node.js"></script> -->
<script type="text/javascript">
  $(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/common/upload';
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

        $("#btn").click(function () {
          // 标题
          var title = $("#title").val();
          // html 格式的内容
          var contentHtml = editor.txt.html();
          // 普通格式的内容
          var contentText = editor.txt.text();
          
          // var tab = $("#tab option:selected").val();
          // var nodeCode = $("#node option:selected").val();
          // alert(contentHtml);
          
          var node = "${node}";
          
       	  // 节点
          var nodeTitle = node ? node : $("#node option:selected").val();
          // 标签
          var tag = $("#tag").val();
          // var avatar = $("#editor").find("img:first").attr("src");
          if(!title || title.length > 120) {
            alert('请输入标题，且最大长度在120个字符以内');
            return false;
          }else if(!nodeTitle){
          alert('请选择一个节点');
          return false;
        }else {
        $.ajax({
          url: '/topic/save',
          type: 'post',
          async: false,
          cache: false,
          dataType: 'json',
          data: {
            title: title,
            content: contentHtml,
            //tab:tab,
            // nodeCode:nodeCode,
            nodeTitle: nodeTitle,
            tag: tag
          },
          success: function(data){
          //console.log(JSON.stringify(data));
          if(data.success != null && data.success == true) {
            window.location.href = "/topic/" + data.data.topic.topicId;
          } else {
            alert(data.error);
          }
        },
        error: function(data){
        	console.log(data);
        	//alert(data.error);
        }
      })
      }
    });
      })
    </script>
  </body>
  </html>