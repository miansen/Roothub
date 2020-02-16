<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="node_header">
                <div class="node_avatar">
                    <div style="float: left; display: inline-block; margin-right: 10px; margin-bottom: initial!important;">
                        <img src="${contextPath}${nodeVO.avatar}" border="0" align="default" width="72">
                    </div>
                </div>
                <div class="node_info">
                    <div class="fr f12"><span>主题总数</span> <strong>${postCount}</strong></div>
                    <a href="/">主页</a> <span class="chevron">&nbsp;›&nbsp;</span> ${nodeVO.nodeName}
                    <div class="sep10"></div>
                    <div class="sep5"></div>
                    <span class="f12">${nodeVO.nodeDesc}</span>
                    <div class="sep10"></div>
                    <div class="node_header_tabs">
                         <a href="${contextPath}/nodes/${nodeVO.nodeName}?tabName=all" class="${tabName eq "all" ? "node_header_tab_current" : "node_header_tab"}">全部</a>
                         <a href="${contextPath}/nodes/${nodeVO.nodeName}?tabName=new" class="${tabName eq "new" ? "node_header_tab_current" : "node_header_tab"}">最新</a>
                    	 <a href="${contextPath}/nodes/${nodeVO.nodeName}?tabName=good" class="${tabName eq "good" ? "node_header_tab_current" : "node_header_tab"}">精华</a>
                    	 <a href="${contextPath}/nodes/${nodeVO.nodeName}?tabName=noReply" class="${tabName eq "noReply" ? "node_header_tab_current" : "node_header_tab"}">等待评论</a>
                    </div>
                </div>
            </div>
            <div class="panel-body paginate-bot">
                <c:forEach var="postVO" items="${postVOPage.list}">
                    <div class="media">
                        <c:if test="${fn:length(postVO.avatar) > 0}">
                            <div class="media-left">
                                <img src="${contextPath}${postVO.avatar}" class="avatar img-circle" alt="">
                            </div>
                        </c:if>
                        <div class="media-body">
                            <div class="title">
                                <c:choose>
                                    <c:when test="${postVO.url != null}">
                                        <a href="${postVO.url}" target="_blank">${postVO.title}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${contextPath}/posts/${postVO.postId}">${postVO.title}</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="tip">
                                <p class="gray">
                                    <c:if test="${postVO.top}">
                                        <span class="label label-primary">置顶</span> <span>•</span>
                                    </c:if>
                                    <c:if test="${postVO.good}">
                                        <span class="label label-primary">精华</span> <span>•</span>
                                    </c:if>
                                    <a href="${contextPath}/users/${postVO.userName}">${postVO.userName}</a>
                                  	<c:if test="${postVO.viewCount > 0}">
                                        <span class="hidden-sm hidden-xs">•</span>
                                        <span class="hidden-sm hidden-xs">${postVO.viewCount}次点击</span>
                                    </c:if>
                                    <c:if test="${postVO.commentCount > 0}">
                                        <span class="hidden-sm hidden-xs">•</span>
                                        <span class="hidden-sm hidden-xs"><a
                                                href="${contextPath}/posts/${postVO.postId}">${postVO.commentCount}个评论</a></span>
                                    </c:if>
                                    <c:if test="${not empty postVO.createDate}">
                                        <span class="hidden-sm hidden-xs">•</span>
                                    	<span>${postVO.createDate}</span>
                                    </c:if>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-footer" id="paginate"></div>
        </div>
    </div>
    <div class="col-md-3 hidden-sm hidden-xs">
         <%@ include file="../common/session.jsp" %>
        <!-- 相邻节点开始 -->
        <c:if test="${parentNodeVO != null 
        || (adjacencyNodeVOPage != null && fn:length(adjacencyNodeVOPage.list) > 0) 
        || (childrenNodeVOPage != null && fn:length(childrenNodeVOPage.list) > 0)}">
	        	<div class="panel panel-default">
	            	<div class="panel-body">
	                	<div class="row">
	                    <c:if test="${parentNodeVO != null}">
	                        <div class="cell" style="border-bottom: 0px solid #e2e2e2;">
	                            <strong class="gray">父节点</strong>
	                            <div class="sep10"></div>
	                            <img src="${contextPath}${parentNodeVO.avatar}" border="0" align="absmiddle" width="24">&nbsp;
	                            <a href="/nodes/${parentNodeVO.nodeName}">${parentNodeVO.nodeName}</a>
	                        </div>
	                    </c:if>
	                    <c:if test="${adjacencyNodeVOPage != null && fn:length(adjacencyNodeVOPage.list) > 0}">
	                        <div class="cell" style="border-bottom: 0px solid #e2e2e2;border-top: 1px solid #e2e2e2;">
	                            <strong class="gray">相关节点</strong>
	                            <c:forEach var="item" items="${adjacencyNodeVOPage.list}" varStatus="status">
	                                <div class="sep10"></div>
	                                <img src="${contextPath}${item.avatar}" border="0" align="absmiddle" width="24">&nbsp;
	                                <a href="/nodes/${item.nodeName}">${item.nodeName}</a>
	                                <div class="sep10"></div>
	                            </c:forEach>
	                        </div>
	                    </c:if>
	                    <c:if test="${childrenNodeVOPage != null && fn:length(childrenNodeVOPage.list) > 0}">
	                        <div class="cell" style="border-bottom: 0px solid #e2e2e2;border-top: 1px solid #e2e2e2;">
	                            <strong class="gray">子节点</strong>
	                            <c:forEach var="item" items="${childrenNodeVOPage.list}" varStatus="status">
	                                <div class="sep10"></div>
	                                <img src="${contextPath}${item.avatar}" border="0" align="absmiddle" width="24">&nbsp;
	                                <a href="/nodes/${item.nodeName}">${item.nodeName}</a>
	                                <div class="sep10"></div>
	                            </c:forEach>
	                        </div>
	                    </c:if>
	                </div>
	        	</div>
	        </div>
        </c:if>
        <!-- 相邻节点结束 -->
    </div>
</div>
<!-- <script src="/default/front/node/js/changeSectionClass.js"></script> -->
<script type="text/javascript">
    var nodeTitle = "${nodeVO.nodeName}";//节点名称
    var nodeCode = "${nodeVO.nodeCode}";//节点编码
    var nodeURL = "${nodeVO.url}";//节点url
    var tabName = "${tabName}";//节点板块
    var count = ${postVOPage.totalRow};//数据总量
    var limit = ${postVOPage.pageSize};//每页显示的条数
    var url = nodeURL + "?tabName=" + tabName + "&pageNumber=";//url
    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }

    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="../layout/footer.jsp" %>