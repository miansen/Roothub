<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="tab panel-heading">
                <ul class="nav nav-pills" id="tab">
                    <%-- <c:forEach var="item" items="${tabList}" varStatus="status">
                        <li class="${item.tabName}"><a href="/?tab=${item.tabName}" class="tab">${item.tabDesc}</a></li>
                    </c:forEach> --%>
                    <li class="all"><a href="/?tab=all">全部</a></li>
                    <li class="hot"><a href="/?tab=hot">最热</a></li>
                    <li class="new"><a href="/?tab=new">最新</a></li>
                    <li class="lonely"><a href="/?tab=lonely">无人问津</a></li>
                    <!-- <li class="member"><a href="/?tab=member" class="tab">关注</a></li> -->
                </ul>
            </div>
            <!-- 节点列表 -->
            <%-- <c:if test="${fn:length(nodeList) > 0}">
          <div class="section node">
            <ul class="nav nav-pills" id="node">
              <c:forEach var="item" items="${nodeList}" varStatus="status">
              <li class="active"><a href="${item.url}">${item.nodeTitle}</a></li>
            </c:forEach>
          </ul>
        </div>
        </c:if> --%>
            <div class="panel-body paginate-bot">
                <c:forEach var="item" items="${page.list}">
                    <div class="media">
                        <c:if test="${fn:length(item.avatar) > 0}">
                            <div class="media-left">
                                    <%-- <a href="/user/${item.author}"> --%><img src="${item.avatar}"
                                                                                  class="avatar img-circle" alt="">
                                <!-- </a> -->
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
                                <%-- <div class="excerpt"><span>${item.excerpt}</span></div> --%>
                            <div class="tip">
                                <p class="gray">
                                    <c:if test="${item.top}">
                                        <span class="label label-primary">置顶</span> <span>•</span>
                                    </c:if>
                                    <c:if test="${item.good}">
                                        <span class="label label-success">精华</span> <span>•</span>
                                    </c:if>
                                    <span><a href="/n/${item.nodeTitle}" class="node">${item.nodeTitle}</a></span>
                                    <span>•</span>
                                    <a href="/user/${item.author}">${item.author}</a>
                                    <c:if test="${item.viewCount > 0}">
                                        <span class="hidden-sm hidden-xs">•</span>
                                        <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                                    </c:if>

                                    <!-- 评论数量 -->
                                    <c:if test="${item.replyCount > 0}">
                                        <span class="hidden-sm hidden-xs">•</span>
                                        <span class="hidden-sm hidden-xs"><a
                                                href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
                                    </c:if>

                                    <span>•</span>
                                    <span><fmt:formatDate type="date"
                                                          value="${item.createDate}"/></span>
                                        <%-- <span>${baseEntity.formatDate(item.createDate)}</span> --%>
                                        <%-- <span class="formate-date">${item.createDate}</span> --%>

                                    <!-- 最后回复用户 -->
                                        <%-- <c:if test="${fn:length(item.lastReplyAuthor) > 0}">
                                        <span>•</span>
                                        <span>最后回复来自 <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span>
                                        </c:if> --%>

                                    <!-- 标签 -->
                                        <%-- <c:if test="${item.tag != null}">
                                        <span>•</span>
                                        <a href="/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a>
                                        </c:if> --%>
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
    <div class="col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default" id="session"></div>
        <!-- 今日热议主题 -->
        <div class="panel panel-default">
            <div class="panel-heading"><span style="color: #ccc;">今日热议主题</span></div>
            <table class="table" style="font-size: 14px;">
                <tbody>
                <c:forEach var="item" items="${findHot}">
                    <tr>
                        <c:if test="${fn:length(item.avatar) > 0}">
                            <td width="24" valign="middle" align="center">
                                <img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;">
                            </td>
                        </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${item.url != null}">
                                    <a href="${item.url}">${item.title}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/topic/${item.topicId}">${item.title}</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 今日等待回复的主题 -->
        <%-- <div class="panel panel-default">
          <div class="panel-heading"><span style="color: #ccc;">今日等待回复主题</span></div>
          <table class="table" style="font-size: 14px;">
            <tbody>
            <c:forEach var="item" items="${findTodayNoReply}">
              <tr>
              <td width="24" valign="middle" align="center">
                    <a href="/user/${item.author}"><img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;"></a>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.url != null}">
                            <a href="${item.url}">${item.title}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/topic/${item.topicId}">${item.title}</a>
                        </c:otherwise>
                    </c:choose>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div> --%>
        <!-- 积分榜 -->
        <div class="panel panel-default">
            <div class="panel-heading"><span style="color: #ccc;">积分榜
                <a class="dark" href="/top100" style="float: right;">TOP 100 &gt;&gt;</a></span>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="inner top100"></div>
                </div>
            </div>
        </div>
        <!-- 最热标签 -->
        <div class="panel panel-default">
            <div class="panel-heading"><span style="color: #ccc;">热门节点</span></div>
            <div class="panel-body">
                <c:forEach var="item" items="${nodeList2}">
                    <a href="${item.url}" class="item_node"><span class="layui-badge layui-bg-primary">${item.nodeTitle}</span></a>
                </c:forEach>
            </div>
        </div>
        <!-- 社区运行状况 -->
        <div class="panel panel-default">
            <div class="panel-heading"><span style="color: #ccc;">社区运行状况</span></div>
            <div class="cell">
                <table cellpadding="5" cellspacing="0" border="0" width="100%">
                    <tbody style="font-size: 14px;">
                    <tr>
                        <td width="80" align="right"><span class="gray">注册会员：</span></td>
                        <td width="auto" align="left"><strong>${countUserAll}</strong></td>
                    </tr>
                    <tr>
                        <td width="80" align="right" style="font-size: 14px;"><span class="gray">主题：</span></td>
                        <td width="auto" align="left"><strong>${countAllTopic}</strong></td>
                    </tr>
                    <tr>
                        <td width="80" align="right"><span class="gray">回复：</span></td>
                        <td width="auto" align="left"><strong>${countAllReply}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="/default/front/index.js"></script>
<script type="text/javascript">
    var tab = "${tab}";//父板块
    var count = ${page.totalRow};//数据总量
    var limit = ${page.pageSize};//每页显示的条数
    var url = "?tab=" + tab + "&p=";//url
    function page() {
        var page = location.search.match(/p=(\d+)/);
        return page ? page[1] : 1;
    }
    var p = page();//当前页数
    paginate(count, limit, p, url);
</script>
<%@ include file="layout/footer.jsp" %>