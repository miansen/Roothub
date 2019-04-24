<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Roothub-${node.nodeTitle}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
 <div class="wrapper">
  <jsp:include page="../components/head.jsp"></jsp:include>
  <div class="row">
    <div class="col-md-9">
      <div class="panel panel-default">
       <div class="node_header">
         <div class="node_avatar">
           <div style="float: left; display: inline-block; margin-right: 10px; margin-bottom: initial!important;">
             <img src="${node.avatarNormal}" border="0" align="default" width="72"></div>
           </div>
           <div class="node_info">
             <div class="fr f12"><span>主题总数</span> <strong>${countTopicByNode}</strong></div>
             <a href="/">主页</a> <span class="chevron">&nbsp;›&nbsp;</span> ${node.nodeTitle}
             <div class="sep10"></div>
             <div class="sep5"></div>
             <span class="f12">${node.nodeDesc}</span>
             <div class="sep10"></div>
             <div class="node_header_tabs">
               <c:if test="${fn:length(nodeTabList) > 0}">
               <c:forEach var="item" items="${nodeTabList}" varStatus="status">
               <a href="${node.url}?s=${item.nodeTabCode}" id="${item.nodeTabCode}" class="node_header_tab">${item.nodeTabTitle}</a>
             </c:forEach>
           </c:if>
         </div>
       </div>
     </div>
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
          <!-- 摘录 -->
          <%-- <div class="excerpt"><span>${item.excerpt}</span></div> --%>
          <div class="tip">
          <p class="gray">
            <c:if test="${item.top}">
            <span class="label label-primary">置顶</span> <span>•</span>
          </c:if>
          <c:if test="${item.good}">
          <span class="label label-primary">精华</span> <span>•</span>
        </c:if>
        <a href="/user/${item.author}">${item.author}</a>
        <span class="hidden-sm hidden-xs">•</span>
        <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
        <!-- 评论 -->
        <!-- <span class="hidden-sm hidden-xs">•</span> -->
        <%-- <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span> --%>
        <span>•</span>
        <span><fmt:formatDate type="date" 
          value="${item.createDate}" /></span>
          <!-- 最后回复用户 -->
          <%-- <c:if test="${item.lastReplyAuthor != null}">
          <span>•</span>
          <span>最后回复来自 <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span>
        </c:if> --%>
        <!-- 标签 -->
        <!-- <span>•</span> -->
        <%-- <a href="/topic/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a> --%>
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
  <div class="panel panel-default" id="session">
  	<div class="panel-body" id="nologin">
  	<h5>n/${node.nodeTitle}</h5>
  	<p>${node.nodeDesc}</p>
  	<p><a href="/topic/create?n=${node.nodeTitle}" style="font-size: 14px;">
  	<button class="btn btn-success">发布话题</button></a>
  	</p>
  	</div>
  </div>
  <!-- 相邻节点 -->
  <div class="panel panel-default">
    <!-- <div class="panel-heading"><span style="color: #ccc;">相关节点</span></div> -->
    <div class="panel-body">
      <div class="row">
      <c:if	test="${parentNode != null}">
      	<div class="cell" style="border-bottom: 0px solid #e2e2e2;">
        	<strong class="gray">父节点</strong>
        	<div class="sep10"></div>
        	<img src="${parentNode.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${parentNode.url}">${parentNode.nodeTitle}</a>
    	</div>
      </c:if>
      <c:if test="${fn:length(adjacencyNode) > 0}">
        <div class="cell" style="border-bottom: 0px solid #e2e2e2;border-top: 1px solid #e2e2e2;">
          <strong class="gray">相关节点</strong>
          <c:forEach var="item" items="${adjacencyNode}" varStatus="status">
          <div class="sep10"></div>
          <img src="${item.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${item.url}">${item.nodeTitle}</a>
          <div class="sep10"></div>
        </c:forEach> 
      </div>
    </c:if>
    <c:if test="${fn:length(childrenNode) > 0}">
    	<div class="cell" style="border-bottom: 0px solid #e2e2e2;border-top: 1px solid #e2e2e2;">
          <strong class="gray">子节点</strong>
          <c:forEach var="item" items="${childrenNode}" varStatus="status">
          <div class="sep10"></div>
          <img src="${item.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${item.url}">${item.nodeTitle}</a>
          <div class="sep10"></div>
        </c:forEach> 
      </div>
    </c:if>
  </div>
</div>
</div>
<!-- 相邻节点 -->
</div>
</div>
</div>
<div id="back2Top" class="backTop___6Q-ki" style="display:none">
  <div class="line___F1WY0"></div>
  <div class="arrow___3UCwo"></div>
</div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<!-- <script src="/resources/js/login_info.js"></script> -->
<script src="/resources/js/formatDate.js"></script>
<script src="/resources/js/node/changeSectionClass.js"></script>
<script type="text/javascript">
 var nodeTitle = "${node.nodeTitle}";//节点名称
 var nodeCode = "${node.nodeCode}";//节点编码
 var nodeURL = "${node.url}";//节点url
 var avatarLarge = "${node.avatarLarge}";//节点背景
 $(".wrapper").css({"background-image":"url("+avatarLarge+")"});
 // console.log(avatarLarge)
 var nodeTabCode = "${nodeTab}";//节点板块
 var count = ${page.totalRow};//数据总量
 var limit = ${page.pageSize};//每页显示的条数
 var url = nodeURL+"?s="+nodeTabCode+"&p=";//url
 function page(){
   var page = location.search.match(/p=(\d+)/);  
   return page ? page[1] : 1;  
 }
 var p = page();//当前页数
 paginate(count,limit,p,url);
</script>
</body>
</html>