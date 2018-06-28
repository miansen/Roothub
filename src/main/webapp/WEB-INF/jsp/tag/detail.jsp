<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>标签-roothub</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="wrapper">
<jsp:include page="../components/head.jsp"></jsp:include>
<div class="row">
  <div class="panel panel-default">
    <div class="panel-body">
      <h4 style="margin-top: 0; margin-bottom: 10px;">
        ${tagName}
        <small>共有${pageByTag.totalRow}篇话题</small>
      </h4>
      <small></small>
      <span></span>
    </div>
    <div class="divide"></div>
    <div class="panel-body paginate-bot">
    <c:forEach var="item" items="${pageByTag.list}">
    <div class="media">
      <div class="media-left">
        <a href="/user/${item.author}"><img src="/resources/images/${item.avatar}" class="avatar" alt=""></a>
      </div>
      <div class="media-body">
        <div class="title">
            <a href="/topic/${item.topicId}">
              ${item.title}
            </a>
        </div>
        <p class="gray">
          <span><a href="/user/${item.author}">${item.author}</a></span>
          <span class="hidden-sm hidden-xs">•</span>
          <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
          <span class="hidden-sm hidden-xs">•</span>
          <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
          <span>•</span>
          <span><fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" />
          </span>
          <span>•</span>
            <a href="/topic/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a>
        </p>
      </div>
    </div>
      <div class="divide mar-top-5"></div>
      </c:forEach>
    </div>
<ul class="pagination pagination-sm pagination2" style="padding-left: 20;"></ul>
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
	$("#biaoqian").addClass("active");
	var url = "/topic/tag/${tagName}?";
	$(".pagination2").pagination("${pageByTag.pageNumber}","${pageByTag.totalPage}",10);
</script>
</body>
</html>