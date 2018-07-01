<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>搜索结果</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="wrapper">
<jsp:include page="components/head.jsp"></jsp:include>
<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-heading">搜索结果</div>
        <div class="panel-body paginate-bot">

  <c:forEach var="item" items="${pageLike.list}">
          <div class="media" id="topic">
            <div class="media-left">
              <a href="/user/${item.author}"><img src="/resources/images/${item.avatar}" class="avatar img-circle" alt=""></a>
            </div>
            <div class="media-body">
              <div class="title">
                <a href="/topic/${item.topicId}">
                  ${item.title}
                </a>
              </div>
              <p class="gray">
              <c:if test="${item.top}">
			  <span class="label label-primary">置顶</span> <span>•</span>
			  </c:if>
			  <c:if test="${item.good}">
			  <span class="label label-primary">精华</span> <span>•</span>
			  </c:if>
                <span><a href="/user/${item.author}">${item.author}</a></span>
                <span class="hidden-sm hidden-xs">•</span>
                <span class="hidden-sm hidden-xs"><a href="/topic/41">${item.replyCount}个评论</a></span>
                <span class="hidden-sm hidden-xs">•</span>
                <span class="hidden-sm hidden-xs">${item.viewCount}次浏览</span>
                <span>•</span>
                <span><fmt:formatDate type="date" 
                  value="${item.createDate}" /></span>
                  <c:if test="${item.lastReplyAuthor != null}">
                  <span>•</span>
                  <span>最后回复来自 <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span>
                  </c:if>
                  <span>•</span>
                  <a href="/topic/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a>
                </p>
              </div>
              <div class="divide mar-top-5"></div>
            </div>
          </c:forEach>
      </div>
      <div class="panel-footer">
          <ul class="pagination pagination-sm pagination2"></ul>
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
<jsp:include page="components/foot.jsp"></jsp:include>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="/resources/js/jquery.js"></script>
<!-- 引入 Bootstrap -->
<script src="/resources/js/bootstrap.min.js"></script>
<!-- 分页 -->
<script src="/resources/js/pagination2.js"></script>
<script src="/resources/js/goTop.js"></script>
<script type="text/javascript">
  var search = "${search}";
  var url = "/search?s="+search+"&";
  $(".pagination2").pagination("${pageLike.pageNumber}","${pageLike.totalPage}",10);
</script>
</body>
</html>