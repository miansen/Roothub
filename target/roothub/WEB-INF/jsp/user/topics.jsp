<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${user.userName}创建的话题</title>
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
      <div class="col-md-9">
        <div class="panel panel-default">
          <div class="panel-heading">${user.userName}创建的话题</div>
          <c:forEach var="item" items="${topicPage.list}">
          <div class="panel-body paginate-bot" style="border-bottom: 1px solid #e2e2e2;">
            <div class="media">
              <div class="media-body">
                <div class="title">
                  <a href="/topic/${item.topicId}">
                    ${item.title}
                  </a>
                </div>
                <p>
                  <span><a href="/user/${item.author}">${item.author}</a></span>
                  <span class="hidden-sm hidden-xs">•</span>
                  <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
                  <span class="hidden-sm hidden-xs">•</span>
                  <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                  <span>•</span>
                  <span><fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" /></span>
                </p>
              </div>
            </div>
          </div>
          </c:forEach>
          <div class="panel-footer">
            <ul class="pagination pagination-sm pagination2"></ul>
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
	var url = "/user/${user.userName}/topics?";
	$(".pagination2").pagination("${topicPage.pageNumber}","${topicPage.totalPage}",10);
</script>
</body>
</html>