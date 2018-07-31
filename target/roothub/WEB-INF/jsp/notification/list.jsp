<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>通知列表</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <script src="/resources/js/logout.js"></script>
</head>
<body>
 <div class="wrapper" style="width: 100%;background-color: #e5e5e5;">
  <jsp:include page="../components/head.jsp"></jsp:include>
<div class="row">
  <div class="col-md-9">
      <div class="panel panel-default">
        <div class="panel-heading">
          <a href="/" style="color: #778087;">roothub</a><span class="chevron">&nbsp;›&nbsp;</span>通知系统
          <span class="pull-right">总共收到通知 ${countByAuthor}</span>
        </div>
        <div class="panel-body">
        <c:forEach var="item" items="${page.list}">
            <div class="media">
              <div class="media-left">
                <img src="/resources/images/${item.avatar}" class="avatar-sm img-circle">
              </div>
              <div class="media-body">
                <div class="gray">
                  <a href="/user/${item.noticeAuthorName}">${item.noticeAuthorName}</a>
                    在
                  <a href="/topic/${item.topicId}">${item.title}</a>
                    里评论了你
                  <span><fmt:formatDate type="date" 
                  value="${item.createDate}" /></span>
                </div>
                  <div class="payload">
                    ${item.noticeContent}
                  </div>
              </div>
            </div>
            </c:forEach>
            <!-- <div class="divide mar-top-5"></div>
          <ul class="pagination pagination-sm pagination2"></ul> -->
        </div>
        <div class="panel-footer">
          <ul class="pagination pagination-sm pagination2"></ul>
          </div>
      </div>
  </div>
  <div class="col-md-3 hidden-sm hidden-xs">
  	<div class="panel panel-default" id="session">
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
<!-- 分页 -->
<script src="/resources/js/pagination2.js"></script>
<script type="text/javascript">
  var url = "/notification/list?";
  $(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);
</script>
</body>
</html>