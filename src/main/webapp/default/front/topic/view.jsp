<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-body topic-detail-header">
                <div class="media">
                    <div class="media-body">
                        <a href="/">主页</a>
                        <input type="hidden" value="${topic.topicId}" id="hidden-topicId">
                        <c:if test="${topic.nodeTitle != null}">
                            <span class="chevron">&nbsp;›&nbsp;</span>
                            <a href="/n/${topic.nodeTitle}" class="topic-detail-node">${topic.nodeTitle}</a>
                        </c:if>
                        <div class="sep10"></div>
                        <c:choose>
                            <c:when test="${topic.url != null}">
                                <h2><a href="${topic.url}" target="_blank">${topic.title}</a></h2>
                            </c:when>
                            <c:otherwise>
                                <h2>${topic.title}</h2>
                            </c:otherwise>
                        </c:choose>
                        <p>
                        <div id="topic_${topic.topicId}_votes" class="votes">
                        <a href="javascript:" onclick="voteTopic(${topic.topicId},true);" class="vote vote_up" title="0 赞同">
                            <li class="fa fa-chevron-up"></li>
                        </a>
                        <a href="javascript:" onclick="voteTopic(${topic.topicId},false);" class="vote vote_down" title="0 反对">
                            <li class="fa fa-chevron-down"></li>
                        </a>
                        </div>
                        <span>•</span>
                        <c:if test="${topic.top}">
                            <span class="label label-primary">置顶</span>
                            <span>•</span>
                        </c:if>
                        <c:if test="${topic.good}">
                            <span class="label label-success">精华</span>
                            <span>•</span>
                        </c:if>
                        <span class="author"><a href="/user/${topic.author}">${topic.author}</a></span>
                        <span>•</span>
                        <span><fmt:formatDate type="both" value="${topic.createDate}"/></span>
                        <span>•</span>
                        <span>${topic.viewCount}次点击</span>
                        </p>
                    </div>
                    <div class="media-right">
                        <img src="${topic.avatar}" class="avatar-lg img-circle">
                    </div>
                </div>
            </div>
            <div class="divide"></div>
            <div class="panel-body topic-detail-content show_big_image">
                ${topic.content}
                <div>
                    <a href="/tag/${topic.tag}">
                        <span class="label label-success">${topic.tag}</span>
                    </a>
                </div>
            </div>
            <div class="panel-footer" style="display: none" id="collect">
                <a href="javascript:window.open('http://service.weibo.com/share/share.php?url=https://roothub.cn/topic/${topic.topicId}?r=${topic.author}&amp;title=${topic.title}', '_blank', 'width=550,height=370'); recordOutboundLink(this, 'Share', 'weibo.com');">分享微博</a>&nbsp;
                <a href="javascript:void(0);" class="collectTopic" onclick="save()"></a> <span class="pull-right"><span id="collectCount">${countByTid}</span>个收藏</span>
            </div>
        </div>
        <c:if test="${topic.replyCount == 0}">
            <div class="panel panel-default">
                <div class="panel-body text-center">目前暂无评论</div>
            </div>
        </c:if>
        <c:if test="${topic.replyCount > 0}">
            <jsp:include page="../reply/list.jsp"></jsp:include>
        </c:if>
        <div class="panel panel-default" id="pinglun" style="display: none">
            <div class="panel-heading">
                添加一条新评论&nbsp;
                <a href="javascript:void(0);" onclick="switchEditor(this)" style="color: #66afe9">
                    <small id="editor-type">Markdown编辑器</small>
                </a>
            </div>
            <div class="panel-body">

                <%--富文本编辑器--%>
                <div id="wangEditor" class="form-group">
                    <div id="wangEditor-content" style="margin-bottom: 10px;"></div>
                </div>

                <%--Markdown编辑器--%>
                <div id="codemirror" class="form-group" style="display: none;">
                    <textarea name="content"
                              id="codemirror-content"
                              class="form-control"
                              placeholder="内容，支持Markdown语法">
                    </textarea>
                </div>

                <button type="button" id="wangEditor-btn" class="btn btn-primary">评论</button>
                <button type="button" id="codemirror-btn" class="btn btn-primary" style="display: none;">评论</button>
                <div class="fr"><a href="/">← Roothub</a></div>
            </div>
        </div>
    </div>
    <div class="col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default" id="session"></div>
    </div>
</div>
<script src="/default/front/topic/js/view.js"></script>
<script type="text/javascript">

    // 数据总量
    var count = ${replyPage.totalRow};
    //每页显示的条数
    var limit = ${replyPage.pageSize};
    //url
    var url = "/topic/${topic.topicId}?p=";

    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }

    var p = page();//当前页数
    paginate(count, limit, p, url);

</script>
<%@ include file="../layout/footer.jsp" %>