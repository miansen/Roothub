<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading"><a href="/">主页</a> / ${user.userName}创建的话题</div>
            <c:forEach var="item" items="${topicPage.list}">
                <div class="panel-body paginate-bot" style="border-bottom: 1px solid #e2e2e2;">
                    <div class="media">
                        <c:if test="${fn:length(item.avatar) > 0}">
                            <div class="media-left">
                                <img src="${item.avatar}" class="avatar img-circle" alt="">
                            </div>
                        </c:if>
                        <div class="media-body">
                            <div class="title">
                                <a href="/topic/${item.topicId}">${item.title}</a>
                            </div>
                            <div class="tip">
                                <p>
                                    <c:if test="${item.top}">
                                        <span class="label label-primary">置顶</span> <span>•</span>
                                    </c:if>
                                    <c:if test="${item.good}">
                                        <span class="label label-primary">精华</span> <span>•</span>
                                    </c:if>
                                    <span><a href="/node/${item.nodeSlug}" class="node">${item.nodeTitle}</a></span>
                                    <span>•</span>
                                    <strong><a href="/user/${item.author}">${item.author}</a></strong>
                                    <span class="hidden-sm hidden-xs">•</span>
                                    <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                                    <span class="hidden-sm hidden-xs">•</span>
                                    <span><fmt:formatDate type="date" value="${item.createDate}"/></span>
                                </p>
                            </div>
                        </div>
                        <div class="media-right">
                            <span class="badge badge-default">
                                <a href="/topic/${item.topicId}">${item.replyCount}</a>
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="panel-footer" id="paginate"></div>
        </div>
    </div>
    <div class="col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default" id="session"></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var count = ${topicPage.totalRow};//数据总量
        var limit = ${topicPage.pageSize};//每页显示的条数
        var url = "/user/topics?p=";//url
        function page() {
            var page = location.search.match(/p=(\d+)/);
            return page ? page[1] : 1;
        }
        var p = page();//当前页数
        paginate(count, limit, p, url);
    });
</script>
<%@ include file="../layout/footer.jsp" %>