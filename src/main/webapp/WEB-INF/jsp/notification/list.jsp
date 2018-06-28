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
          通知
          <span class="pull-right">总共收到通知 ${countByAuthor}</span>
        </div>
        <div class="panel-body">
        <c:forEach var="item" items="${page.list}">
            <div class="media">
              <div class="media-left">
                <img src="/resources/images/${item.avatar}" class="avatar-sm">
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
            <div class="divide mar-top-5"></div>
          <ul class="pagination pagination-sm pagination2"></ul>
        </div>
      </div>
  </div>
  <div class="col-md-3 hidden-sm hidden-xs"></div>
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