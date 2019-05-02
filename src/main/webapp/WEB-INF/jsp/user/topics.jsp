<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${user.userName}创建的话题</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <!-- 引入layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
  <div class="wrapper">
    <jsp:include page="../components/head.jsp"></jsp:include>
    <div class="row">
      <div class="col-md-9">
        <div class="panel panel-default">
          <div class="panel-heading"><a href="/">主页</a> / ${user.userName}创建的话题</div>
          <c:forEach var="item" items="${topicPage.list}">
          <div class="panel-body paginate-bot" style="border-bottom: 1px solid #e2e2e2;">
            <div class="media">
            <c:if test="${fn:length(item.avatar) > 0}">
            <div class="media-left">
              <%-- <a href="/user/${item.author}"> --%><img src="${item.avatar}" class="avatar img-circle" alt=""><!-- </a> -->
            </div>
            </c:if>
              <div class="media-body">
                <div class="title">
                  <a href="/topic/${item.topicId}">
                    ${item.title}
                  </a>
                </div>
                <div class="tip">
                <p>
                  <c:if test="${item.top}">
			      <span class="label label-primary">置顶</span> <span>•</span>
			      </c:if>
			      <c:if test="${item.good}">
			      <span class="label label-primary">精华</span> <span>•</span>
			      </c:if>
			      <span><a href="/node/${item.nodeSlug}" class="node">${item.nodeTitle}</a></span>
			      <span>•</span>
                  <strong><a href="/user/${item.author}">${item.author}</a></strong>
                  <span class="hidden-sm hidden-xs">•</span>
                  <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                  <!-- <span>•</span> -->
                  <%-- <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span> --%>
                  <span class="hidden-sm hidden-xs">•</span>           
                  <span><fmt:formatDate type="date" value="${item.createDate}" /></span>
                  <%-- <c:if test="${item.lastReplyAuthor != null}">
                  <span>•</span>
                  <span>最后回复来自 <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span>
                  </c:if> --%>
                  <!-- <span>•</span> -->
                  <%-- <a href="/topic/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a> --%>
                </p>
                </div>
              </div>
              <div class="media-right"><span class="badge badge-default"><a href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
            </div>
          </div>
          </c:forEach>
          <div class="panel-footer" id="paginate"></div>
        </div>
      </div>
      <div class="col-md-3 hidden-sm hidden-xs">
		<div class="panel panel-default" id="session"></div>
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
<script src="/resources/js/pagination2.js"></script>
<script src="/resources/js/goTop.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script src="/resources/js/login_info.js"></script>
<script type="text/javascript">
	$(function(){
		/* var url = "/user/${user.userName}/topics?";
		$(".pagination2").pagination("${topicPage.pageNumber}","${topicPage.totalPage}",10); */
		 var count = ${topicPage.totalRow};//数据总量
		 var limit = ${topicPage.pageSize};//每页显示的条数
		 var url = "/user/topics?p=";//url
		 function page(){
		    var page = location.search.match(/p=(\d+)/);  
		    return page ? page[1] : 1;  
		 }
		 var p = page();//当前页数
		 paginate(count,limit,p,url);
	});
</script>
</body>
</html>