<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default" id="reply">
    <div class="cell"><span class="gray">${topic.replyCount} 回复</span></div>
    <%int i = 1;%>
    <c:forEach var="item" items="${commentVOPage.list}">
        <div id="r_${item.commentId}" class="cell" style="padding-bottom: 0px;">
            <table cellpadding="0" cellspacing="0" border="0" width="100%" class="pinglun_table">
                <tbody>
                <tr>
                    <td width="48" valign="top" align="center">
                        <a href="${contextPath}/users/${item.userName}">
                            <img src="${contextPath}${item.userAvatar}" class="user-avatar img-circle" alt=""/>
                        </a>
                    <td width="10" valign="top"></td>
                    <td width="auto" valign="top" align="left">
                        <div class="fr"> &nbsp; &nbsp; <span class="no"><%=i++%></span></div>
                        <div class="sep3"></div>
                        <strong>
                            <a href="${contextPath}/users/${item.userName}" class="dark">${item.userName}</a>
                        </strong>&nbsp;
                        <span class="formate-date-reply ago">
                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${item.createDate}"/>
                        </span>
                        <div class="sep5"></div>
                        <div class="comment_content">${item.content}</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:forEach>
    <div class="panel-footer" id="paginate"></div>
</div>