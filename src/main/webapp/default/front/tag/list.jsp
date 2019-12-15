<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">全部标签</div>
        <div class="panel-body">
            <div class="row">
                <c:forEach var="item" items="${tag.list}">
                    <div class="col-md-3" style="margin-bottom: 10px; padding-left: 10px;">
                        <a href="/tag/${item.tag}">
                            <span class="label label-success">${item.tag}</span>
                        </a>
                        <span class="text-muted">x ${item.number}</span>
                        <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="panel-footer" id="paginate"></div>
    </div>
</div>
<script type="text/javascript">
    var count = ${tag.totalRow};//数据总量
    var limit = ${tag.pageSize};//每页显示的条数
    var url = "/tags?p=";//url
    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }
    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="../layout/footer.jsp" %>