<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>学习互助系统-资讯管理</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <!-- <script src="/resources/js/logout.js"></script> -->
  <!-- 引入layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
 <div class="wrapper">
  <jsp:include page="components/head.jsp"></jsp:include>
    <div class="row">
      <div class="col-md-9">
        <div class="panel panel-default">
        	<div class="tab panel-heading">
        	<ul class="nav nav-pills" id="tab">
        	<%-- <c:forEach var="item" items="${tabList}" varStatus="status">
        		<li class="${item.tabName}"><a href="/?tab=${item.tabName}" class="tab">${item.tabDesc}</a></li>
        	</c:forEach> --%>
        	<li class="all"><a href="/?tab=all&statusCd=1400">全部</a></li>
        	<li class="hot"><a href="/?tab=hot&statusCd=1400">最热</a></li>
        	<li class="new"><a href="/?tab=new&statusCd=1400">最新</a></li>
        	<li class="lonely"><a href="/?tab=lonely&statusCd=1400">无人问津</a></li>
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
              <%-- <a href="/user/${item.author}"> --%><img src="${item.avatar}" class="avatar img-circle" alt=""><!-- </a> -->
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
                	<span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
                </c:if>
                
                <span>•</span>
                <span><fmt:formatDate type="date" 
                  value="${item.createDate}" /></span>
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
            <div class="media-right"><span class="badge badge-default"><a href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
            <div class="divide mar-top-5"></div>
          </div>
          </c:forEach>
      </div>
          <div class="panel-footer" id="paginate"></div>
    </div>
  </div>
  <div class="col-md-3 hidden-sm hidden-xs">
  	<c:if test="${sessionScope.user != null}">
					<div class="panel panel-default" id="session">
						<div class="panel-body">
							<div class="media">
								<div class="media-left">
									<a href="/user/${sessionScope.user.userName}"> <img
										src="${sessionScope.user.avatar}" title=""
										class="user-avatar img-circle">
									</a>
								</div>
								<div class="media-body">
									<div class="media-heading">
										<strong><a href="/user/${sessionScope.user.userName}">${sessionScope.user.userName}</a></strong>
										<div style="color: #7A7A7A; font-size: 12px; margin-top: 5px;">
											<i>${sessionScope.user.signature}</i>
										</div>
									</div>
								</div>
								<div style="margin-top: 15px;">
									<a href="/topic/create?statusCd=1400&statusName资讯管理" style="font-size: 14px;"><button
											class="btn btn-success">发布资讯</button></a>
								</div>
							</div>
							<div class="sep10" style="height: 10px;"></div>
							<table cellpadding="0" cellspacing="0" border="0" width="100%"
								class="table_fade" style="font-size: 14px;">
								<tbody>
									<tr>
										<td width="33%" align="center"><a href="/user/topics"
											class="dark" style="display: block;"><span class="bigger">${countTopic}</span>
												<div class="sep3"></div> <span class="fade">我的主题</span></a></td>
										<td width="34%"
											style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);"
											align="center"><a href="/collect/topics" class="dark"
											style="display: block;"><span class="bigger">${countCollect}</span>
												<div class="sep3"></div> <span class="fade">我的收藏</span></a></td>
										<td width="33%" align="center"><a href="/follow/topics"
											class="dark" style="display: block;"><span class="bigger">${countFollow}</span>
												<div class="sep3"></div> <span class="fade">特别关注</span></a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer" style="background-color: white">
							<div class="row">
								<span class="col-md-6"><a href="/notification/list"><span
										id="n_count">${countNotReadNotice}</span> 条未读消息</a></span> <span class="col-md-6 text-right">积分：<a
									href="/top100">${countScore}</a></span>
							</div>
						</div>
					</div>
				</c:if>
				
				<!-- 今日热议主题 -->
    <div class="panel panel-default">
      <div class="panel-heading"><span style="color: #ccc;">今日热议主题</span></div>
      <table class="table" style="font-size: 14px;">
        <tbody>
        <c:forEach var="item" items="${findHot}">
          <tr>
          <c:if test="${fn:length(item.avatar) > 0}">
          <td width="24" valign="middle" align="center">
                <%-- <a href="/user/${item.author}"> --%><img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;"><!-- </a> -->
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
    <div class="panel panel-default">
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
    </div>
    <!-- 积分榜 -->
    <div class="panel panel-default">
    <div class="panel-heading"><span style="color: #ccc;">积分榜  <a class="dark" href="/top100">TOP 100 &gt;&gt;</a></span></div>
    <div class="panel-body">
      <div class="row">
      <div class="inner top100"></div>
      </div>
    </div>
  </div>
    <!-- 最热标签 -->
    <div class="panel panel-default">
    <div class="panel-heading"><span style="color: #ccc;">热门板块</span></div>
    <div class="panel-body">
      <div class="row">
      <c:forEach var="item" items="${nodeList2}">
          <div class="col-md-6" style="margin-bottom: 10px; padding-left: 10px;">
            <%-- <a href="${item.url}">
              <span>n/${item.nodeTitle}</span>
            </a> --%>
            <%-- <span class="text-muted">x ${item.number}</span> --%>
            <a href="${item.url}"><span class="label label-primary text-muted">${item.nodeTitle}</span></a>
            <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
          </div>
          </c:forEach>
      </div>
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
</div>
<div id="back2Top" class="backTop___6Q-ki" style="display:none">
<div class="line___F1WY0"></div>
<div class="arrow___3UCwo"></div>
</div>
</div>
<jsp:include page="components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/index.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<!-- <script src="/resources/js/login_info.js"></script> -->
<script src="/resources/js/formatDate.js"></script>
<script type="text/javascript">
//var tab = "${tab}";//板块
var tab = "${tab}";//父板块
//var url = "/?tab="+tab+"&ptab="+ptab+"&"
//$(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);
 var count = ${page.totalRow};//数据总量
 var limit = ${page.pageSize};//每页显示的条数
 var url = "?tab="+tab+"&p=";//url
 function page(){
     var page = location.search.match(/p=(\d+)/);  
     return page ? page[1] : 1;  
 }
 var p = page();//当前页数
 //console.log("p:"+p);
 //console.log(count);
 //console.log(url);
 paginate(count,limit,p,url);
</script>
</body>
</html>