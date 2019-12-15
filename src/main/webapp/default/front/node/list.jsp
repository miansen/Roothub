<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">全部节点</div>
        <div class="panel-body">
            <div class="row">
                <c:forEach var="item" items="${nodeList}">
                    <div class="col-md-2" style="margin-bottom: 10px; padding-left: 10px;">
                        <a href="${item.url}">
                            <span class="label label-primary">${item.nodeTitle}</span>
                        </a>
                        <span class="text-muted"></span>
                        <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>