<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>通知列表</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <!-- 引入layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
  <!-- <script src="/resources/js/logout.js"></script> -->
</head>
<body>
 <div class="wrapper" style="width: 100%;background-color: #e5e5e5;">
  <jsp:include page="../components/head.jsp"></jsp:include>
<div class="row">
  <div class="col-md-9">
      <div class="panel panel-default">
        <div class="panel-heading">
          <a href="/" style="color: #778087;">主页</a><span class="chevron">&nbsp;›&nbsp;</span>通知系统
          <span class="pull-right">总共收到通知 ${countByAuthor}</span>
        </div>
        <div class="panel-body">
        <c:forEach var="item" items="${page.list}">
            <div class="media">
              <div class="media-left">
                <img src="${item.avatar}" class="avatar-sm img-circle">
              </div>
              <div class="media-body">
                <div class="gray">
                  <a href="/user/${item.noticeAuthorName}">${item.noticeAuthorName}</a>
                    在
                  <a href="/topic/${item.topicId}">${item.title}</a>
                    里评论了你
                  <span><fmt:formatDate type="date" 
                  value="${item.createDate}" /></span>
                </div>
                  <div class="payload">
                    ${item.noticeContent}
                  </div>
              </div>
            </div>
            </c:forEach>
        </div>
          <div class="panel-footer" id="paginate"></div>
      </div>
  </div>
  <div class="col-md-3 hidden-sm hidden-xs">
		<div class="panel panel-default" id="session"></div>
  </div>
</div>
  </div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script src="/resources/js/login_info.js"></script>
<script type="text/javascript">
  var count = ${page.totalRow};//数据总量
  var limit = ${page.pageSize};//每页显示的条数
  var url = "/notification/list?p=";//url
  function page(){
	   var page = location.search.match(/p=(\d+)/);  
	   return page ? page[1] : 1;  
	 }
  var p = page();//当前页数
  paginate(count,limit,p,url);
</script>
</body>
</html>