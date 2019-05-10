<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>标签-roothub</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <!-- 引入layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
<div class="wrapper">
<jsp:include page="../components/head.jsp"></jsp:include>
<div class="row">
  <div class="panel panel-default">
    <div class="panel-heading"><h4 style="margin-top: 0; margin-bottom: 0px;">
        ${tagName}
        <small>共有${pageByTag.totalRow}篇话题</small>
      </h4></div>
    <div class="panel-body paginate-bot">
    <c:forEach var="item" items="${pageByTag.list}">
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
                	<a href="${item.url}">${item.title}</a>
                </c:when>
                <c:otherwise>
                	<a href="/topic/${item.topicId}">${item.title}</a>
                </c:otherwise>
            </c:choose>
        </div>
        <%-- <div class="excerpt"><span>${item.excerpt}</span></div> --%>
        <div class="tip">
        <p class="gray">
          <a href="/node/${item.nodeSlug}" class="node">${item.nodeTitle}</a>
		  <span>•</span>
          <strong><a href="/user/${item.author}">${item.author}</a></strong>
          <span class="hidden-sm hidden-xs">•</span>
          <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
          <!-- 评论 -->
          <!-- <span class="hidden-sm hidden-xs">•</span> -->
          <%-- <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span> --%>
          <span>•</span>
          <%-- <span>${baseEntity.formatDate(item.createDate)}</span> --%>
          <%-- <span class="formate-date">${item.createDate}</span> --%>
          <span><fmt:formatDate type="date" value="${item.createDate}" /></span>
          <c:if test="${item.lastReplyAuthor != null}">
          <!-- 最后回复用户 -->
          <!-- <span>•</span> -->
          <%-- <span>最后回复来自 <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span> --%>
          </c:if>
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
<script src="/resources/js/goTop.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script src="/resources/js/formatDate.js"></script>
<script src="/resources/js/tag/detail.js"></script>
<script type="text/javascript">
	$("#biaoqian").addClass("active");
	//var url = "/topic/tag/${tagName}?";
	//$(".pagination2").pagination("${pageByTag.pageNumber}","${pageByTag.totalPage}",10);
	var count = ${pageByTag.totalRow};//数据总量
	  var limit = ${pageByTag.pageSize};//每页显示的条数
	  var url = "/tag/${tagName}?p=";//url
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