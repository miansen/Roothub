<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          <div class="panel-heading"><a href="/">Roothub</a> / ${user.userName}创建的话题</div>
          <c:forEach var="item" items="${topicPage.list}">
          <div class="panel-body paginate-bot" style="border-bottom: 1px solid #e2e2e2;">
            <div class="media">
              <div class="media-body">
                <div class="title">
                  <a href="/topic/${item.topicId}">
                    ${item.title}
                  </a>
                </div>
                <p>
                  <span><a href="/user/${item.author}">${item.author}</a></span>
                  <span class="hidden-sm hidden-xs">•</span>
                  <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
                  <span class="hidden-sm hidden-xs">•</span>
                  <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                  <span>•</span>
                  <span><fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" /></span>
                </p>
              </div>
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