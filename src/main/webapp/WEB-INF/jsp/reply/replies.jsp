<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="panel panel-default" id="reply">
<div class="cell"><span class="gray">${topic.replyCount} 回复</span></div>
<%int i=1;%>
<c:forEach var="item" items="${replyPage.list}">
    <div id="r_${item.replyId}" class="cell">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tbody><tr>
            <td width="48" valign="top" align="center"><a href="/user/${item.replyAuthorName}"><img src="/resources/images/${item.avatar}" class="avatar" alt=""/></a>
            <td width="10" valign="top"></td>
            <td width="auto" valign="top" align="left"><div class="fr"> &nbsp; &nbsp; <span class="no"><%=i++%></span></div>
                <div class="sep3"></div>
                <strong><a href="/user/${item.replyAuthorName}" class="dark">${item.replyAuthorName}</a></strong>&nbsp; &nbsp;<span class="ago"><fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" /></span> 
                <div class="sep5"></div>
                <div class="reply_content">${item.replyContent}</div>
            </td>
        </tr>
    </tbody></table>
</div>
</c:forEach>
 <ul class="pagination pagination-sm pagination2" style="padding-left: 20;"></ul>
</div>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="/resources/js/jquery.js"></script>
<!-- 引入 Bootstrap -->
<script src="/resources/js/bootstrap.min.js"></script>
<!-- 分页 -->
<script src="/resources/js/pagination2.js"></script>
<script type="text/javascript">
	var url = "/topic/${topic.topicId}?";
	$(".pagination2").pagination("${replyPage.pageNumber}","${replyPage.totalPage}",10);
</script>
</body>
</html>