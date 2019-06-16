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
                            <img src="${user.avatar}" border="0" align="default" style="border-radius: 4px;" width="73" height="73px"/>
                            <div class="sep10"></div>
                        </td>
                        <td width="10"></td>
                        <td width="auto" valign="top" align="left">
                            <c:if test="${user2 != null && user2.userId != user.userId}">
                                <div class="fr">
                                    <button class="btn btn-follow" onclick="save()" id="follow">加入特别关注</button>
                                    <div class="sep10"></div>
                                    <button class="btn btn-warning">Block</button>
                                </div>
                            </c:if>
                            <h1 title="${user.userId}" id="user_id" class="user_id">${user.userName}</h1>
                            <span class="gray" style="font-size: 14px;">Roothub 第 ${user.userId} 号会员，加入于
                                <fmt:formatDate type="both" value="${user.createDate}"/>
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
                <div class="fl">
                    <img src="${user.avatar}" width="24" style="border-radius: 24px; margin-top: -2px;" border="0">
                </div>
                <a href="javascript:void(0);" onclick="topicList()" class="cell_tab_current">主题</a>
                <a href="javascript:void(0);" onclick="replyList()" class="cell_tab">评论</a>
                <a href="javascript:void(0);" onclick="collectList()" class="cell_tab">收藏</a>
                <a href="javascript:void(0);" onclick="followList()" class="cell_tab">关注</a>
                <a href="javascript:void(0);" onclick="fansList()" class="cell_tab">粉丝</a>
                <a href="javascript:void(0);" onclick="topicQnaList()" class="cell_tab">提问</a>
            </div>
            <div class="itemList"></div>
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
<script src="/default/front/user/js/view.js"></script>
<%@ include file="../layout/footer.jsp" %>