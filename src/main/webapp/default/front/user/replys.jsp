<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading">${user.userName}评论的话题</div>
            <table class="table table-striped">
                <tbody>
                <c:forEach var="item" items="${replyPage.list}">
                    <tr>
                        <td>
                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${item.createDate}"/>
                            评论了
                            <a href="/user/${item.author}">${item.author}</a>
                            创建的话题 ›
                            <a href="/topic/${item.topicId}">${item.title}</a>
                        </td>
                    </tr>
                    <tr class="user_comments">
                        <td class="show_big_image">${item.replyContent}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="panel-footer">
                <ul class="pagination pagination-sm pagination2"></ul>
            </div>
        </div>
    </div>
    <jsp:include page="../common/right.jsp"></jsp:include>
</div>
<script type="text/javascript">
    var url = "/user/${user.userName}/replys?";
    $(".pagination2").pagination("${replyPage.pageNumber}", "${replyPage.totalPage}", 10);
</script>
<%@ include file="../layout/footer.jsp" %>