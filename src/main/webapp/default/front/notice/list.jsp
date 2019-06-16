<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="/" style="color: #778087;">主页</a><span class="chevron">&nbsp;›&nbsp;</span>通知系统
                <span class="pull-right">总共收到通知 ${countByAuthor}</span>
            </div>
            <div class="panel-body">
                <c:forEach var="item" items="${page.list}">
                    <div class="media">
                        <div class="media-left">
                            <img src="${item.avatar}" class="avatar-sm img-circle">
                        </div>
                        <div class="media-body">
                            <div class="gray">
                                <a href="/user/${item.noticeAuthorName}">${item.noticeAuthorName}</a>
                                在
                                <a href="/topic/${item.topicId}">${item.title}</a>
                                里评论了你
                                <span><fmt:formatDate type="date" value="${item.createDate}"/></span>
                            </div>
                            <div class="payload">${item.noticeContent}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-footer" id="paginate"></div>
        </div>
    </div>
    <div class="col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default" id="session"></div>
    </div>
</div>
<script type="text/javascript">
    // 数据总量
    var count = ${page.totalRow};
    // 每页显示的条数
    var limit = ${page.pageSize};
    // url
    var url = "/notification/list?p=";
    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }

    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="../layout/footer.jsp" %>