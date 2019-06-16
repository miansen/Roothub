<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">搜索结果</div>
            <div class="panel-body paginate-bot">
                <c:forEach var="item" items="${pageLike.list}">
                    <div class="media" id="topic">
                        <c:if test="${fn:length(item.avatar) > 0}">
                            <div class="media-left">
                                <img src="${item.avatar}" class="avatar img-circle" alt="">
                            </div>
                        </c:if>
                        <div class="media-body">
                            <div class="title">
                                <c:choose>
                                    <c:when test="${item.url != null}">
                                        <a href="${item.url}" target="_blank">${item.title}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/topic/${item.topicId}">${item.title}</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="tip">
                                <p class="gray">
                                    <c:if test="${item.top}">
                                        <span class="label label-primary">置顶</span> <span>•</span>
                                    </c:if>
                                    <c:if test="${item.good}">
                                        <span class="label label-primary">精华</span> <span>•</span>
                                    </c:if>
                                    <a href="/node/${item.nodeSlug}" class="node">${item.nodeTitle}</a>
                                    <span>•</span>
                                    <strong><a href="/user/${item.author}">${item.author}</a></strong>
                                    <span class="hidden-sm hidden-xs">•</span>
                                    <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                                    <span>•</span>
                                    <span><fmt:formatDate type="date" value="${item.createDate}"/></span>
                                </p>
                            </div>
                        </div>
                        <div class="media-right">
                            <span class="badge badge-default">
                                <a href="/topic/${item.topicId}">${item.replyCount}</a>
                            </span>
                        </div>
                        <div class="divide mar-top-5"></div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-footer" id="paginate"></div>
        </div>
    </div>
</div>
<script src="/default/front/search/js/search.js"></script>
<script type="text/javascript">
    var search = "${search}";
    // 数据总量
    var count = ${pageLike.totalRow};
    // 每页显示的条数
    var limit = ${pageLike.pageSize};
    // url
    var url = "/search?s=" + search + "&p=";

    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }

    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="../layout/footer.jsp" %>