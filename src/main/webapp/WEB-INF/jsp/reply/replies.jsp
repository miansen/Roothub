<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="panel panel-default" id="reply">
<div class="cell"><span class="gray">${topic.replyCount} 回复</span></div>
<%int i=1;%>
<c:forEach var="item" items="${replyPage.list}">
    <div id="r_${item.replyId}" class="cell" style="padding-bottom: 0px;">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="pinglun_table">
        <tbody><tr>
            <td width="48" valign="top" align="center"><a href="/user/${item.replyAuthorName}"><img src="${item.avatar}" class="user-avatar img-circle" alt=""/></a>
            <td width="10" valign="top"></td>
            <td width="auto" valign="top" align="left"><div class="fr"> &nbsp; &nbsp; <span class="no"><%=i++%></span></div>
                <div class="sep3"></div>
                <strong><a href="/user/${item.replyAuthorName}" class="dark">${item.replyAuthorName}</a></strong>&nbsp;
                <span class="formate-date-reply ago">
                <fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" />
                  <%-- ${item.createDate} --%>
                  </span> 
                <div class="sep5"></div>
                <div class="reply_content">${item.replyContent}</div>
            </td>
        </tr>
    </tbody></table>
</div>
</c:forEach>
<div class="panel-footer" id="paginate"></div>
</div>
</body>
</html>