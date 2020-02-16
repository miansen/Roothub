<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="box">
            <div class="cell">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tbody>
                    <tr>
                        <td width="73" valign="top" align="center">
                            <img src="${userVO.avatar}" border="0" align="default" style="border-radius: 4px;" width="73" height="73px"/>
                            <div class="sep10"></div>
                        </td>
                        <td width="10"></td>
                        <td width="auto" valign="top" align="left">
                            <c:if test="${sessionScope.user != null && sessionScope.user.userId != userVO.userId}">
                                <div class="fr">
                                    <button class="btn btn-follow" onclick="save()" id="follow">加入特别关注</button>
                                    <div class="sep10"></div>
                                    <button class="btn btn-warning">Block</button>
                                </div>
                            </c:if>
                            <h1 title="${userVO.userId}" id="user_id" class="user_id">${userVO.userName}</h1>
                            <span class="gray" style="font-size: 14px;">Roothub 正式会员，加入于
                                ${userVO.createDate}。
                                <div class="sep5"></div>
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="sep5"></div>
            </div>
        </div>
        <div class="sep20"></div>
        <div class="panel panel-default">
            <div class="cell_tabs">
                <a href="javascript:void(0);" onclick="topicList()" class="cell_tab_current">帖子</a>
                <a href="javascript:void(0);" onclick="replyList()" class="cell_tab">评论</a>
                <a href="javascript:void(0);" onclick="collectList()" class="cell_tab">收藏</a>
                <a href="javascript:void(0);" onclick="followList()" class="cell_tab">关注</a>
                <a href="javascript:void(0);" onclick="fansList()" class="cell_tab">粉丝</a>
                <a href="javascript:void(0);" onclick="topicQnaList()" class="cell_tab">提问</a>
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
                                        <span class="label label-primary">置顶</span>
                                        <span>•</span>
                                    </c:if>
                                    <c:if test="${postVO.good}">
                                        <span class="label label-primary">精华</span>
                                        <span>•</span>
                                    </c:if>
                                    <a href="${contextPath}/users/${postVO.userName}">${postVO.userName}</a>
                                    <c:if test="${not empty postVO.nodeName}">
                                    	<span>•</span>
                                     	<span><a href="/n/${postVO.nodeName}" class="node">${postVO.nodeName}</a></span>
                                     </c:if>
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
        </div>
        <button id="toggleBigImageBtn" data-toggle="modal" class="hidden" data-target="#showBigImageModal"></button>
        <div class="modal fade" tabindex="-1" role="dialog" id="showBigImageModal">
            <div class="modal-dialog" style="width: 98%" role="document">
                <div class="modal-content">
                    <div class="modal-body text-center">
                        <img src="" id="bigImage" style="max-width: 100%;" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- <script src="/default/front/user/js/view.js"></script> -->
<%@ include file="../layout/footer.jsp" %>