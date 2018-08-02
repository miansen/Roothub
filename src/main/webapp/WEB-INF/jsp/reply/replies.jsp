<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入 Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <!-- 引入layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="panel panel-default" id="reply">
<div class="cell"><span class="gray">${topic.replyCount} 回复</span></div>
<%int i=1;%>
<c:forEach var="item" items="${replyPage.list}">
    <div id="r_${item.replyId}" class="cell">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="pinglun_table">
        <tbody><tr>
            <td width="48" valign="top" align="center"><a href="/user/${item.replyAuthorName}"><img src="/resources/images/${item.avatar}" class="avatar img-circle" alt=""/></a>
            <td width="10" valign="top"></td>
            <td width="auto" valign="top" align="left"><div class="fr"> &nbsp; &nbsp; <span class="no"><%=i++%></span></div>
                <div class="sep3"></div>
                <strong><a href="/user/${item.replyAuthorName}" class="dark">${item.replyAuthorName}</a></strong>&nbsp; &nbsp;<span class="ago"><fmt:formatDate type="both" 
                  dateStyle="medium" timeStyle="short" 
                  value="${item.createDate}" /></span> 
                <div class="sep5"></div>
                <div class="reply_content">${item.replyContent}</div>
            </td>
        </tr>
    </tbody></table>
</div>
</c:forEach>
<!-- <div class="panel-footer">
   <ul class="pagination pagination-sm pagination2"></ul>
</div> -->
<div class="panel-footer" id="paginate"></div>
</div>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="/resources/js/jquery.js"></script>
<!-- 引入 Bootstrap -->
<script src="/resources/js/bootstrap.min.js"></script>
<!-- 分页 -->
<!-- <script src="/resources/js/pagination2.js"></script> -->
<!-- 引入layui.js -->
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script type="text/javascript">
	//var url = "/topic/${topic.topicId}?";
	//$(".pagination2").pagination("${replyPage.pageNumber}","${replyPage.totalPage}",10);
	 var count = ${replyPage.totalRow};//数据总量
	 var limit = ${replyPage.pageSize};//每页显示的条数
	 var url = "/topic/${topic.topicId}?p=";//url
	 function page(){
	     var page = location.search.match(/p=(\d+)/);  
	     return page ? page[1] : 1;  
	 }
	 var p = page();//当前页数
	 console.log("p:"+p);
	 //console.log(count);
	 //console.log(url);
	 paginate(count,limit,p,url);
</script>
</body>
</html>