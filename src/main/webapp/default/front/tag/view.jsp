<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 style="margin-top: 0; margin-bottom: 0px;">${tagName}
                <small>共有${pageByTag.totalRow}篇话题</small>
            </h4>
        </div>
        <div class="panel-body paginate-bot">
            <c:forEach var="item" items="${pageByTag.list}">
                <div class="media">
                    <c:if test="${fn:length(item.avatar) > 0}">
                        <div class="media-left">
                            <img src="${item.avatar}" class="avatar img-circle" alt="">
                        </div>
                    </c:if>
                    <div class="media-body">
                        <div class="title">
                            <c:choose>
                                <c:when test="${item.url != null}">
                                    <a href="${item.url}">${item.title}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/topic/${item.topicId}">${item.title}</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="tip">
                            <p class="gray">
                                <a href="/node/${item.nodeSlug}" class="node">${item.nodeTitle}</a>
                                <span>•</span>
                                <strong><a href="/user/${item.author}">${item.author}</a></strong>
                                <span class="hidden-sm hidden-xs">•</span>
                                <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                                <span>•</span>
                                <span><fmt:formatDate type="date" value="${item.createDate}"/></span>
                                <c:if test="${item.lastReplyAuthor != null}">
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <div class="media-right"><span class="badge badge-default"><a
                            href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
                    <div class="divide mar-top-5"></div>
                </div>
            </c:forEach>
        </div>
        <div class="panel-footer" id="paginate"></div>
    </div>
</div>
<script src="/default/front/tag/js/detail.js"></script>
<script type="text/javascript">
    $("#biaoqian").addClass("active");
    // 数据总量
    var count = ${pageByTag.totalRow};
    // 每页显示的条数
    var limit = ${pageByTag.pageSize};
    // url
    var url = "/tag/${tagName}?p=";

    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }

    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="../layout/footer.jsp" %>