<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${user.userName}评论的话题</title>
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
          <div class="panel-heading">${user.userName}评论的话题</div>
          <table class="table table-striped">
            <tbody>
            <c:forEach var="item" items="${replyPage.list}">
            <tr>
              <td>
                <fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" />
                评论了
                <a href="/user/${item.author}">${item.author}</a>
                创建的话题 › <a href="/topic/${item.topicId}">${item.title}</a>
              </td>
            </tr>
            <tr class="user_comments">
              <td class="show_big_image">
                ${item.replyContent}
              </td>
            </tr>
            </c:forEach>
          </tbody></table>
          <div class="panel-footer">
            <ul class="pagination pagination-sm pagination2"></ul>
          </div>
        </div>
      </div>
      <jsp:include page="../components/right.jsp"></jsp:include>
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
<!-- 分页 -->
<script src="/resources/js/pagination2.js"></script>
<script src="/resources/js/goTop.js"></script>
<script type="text/javascript">
	var url = "/user/${user.userName}/replys?";
	$(".pagination2").pagination("${replyPage.pageNumber}","${replyPage.totalPage}",10);
</script>
</body>
</html>