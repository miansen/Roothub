<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <div class="panel-heading">全部标签</div>
    <div class="panel-body">
      <div class="row">
      <c:forEach var="item" items="${tag.list}">
          <div class="col-md-3" style="margin-bottom: 10px; padding-left: 10px;">
            <a href="/tag/${item.tag}">
              <span class="label label-success">${item.tag}</span>
            </a>
            <span class="text-muted">x ${item.number}</span>
            <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
          </div>
          </c:forEach> 
      </div>
    </div>
	<div class="panel-footer" id="paginate"></div>
  </div>
</div>
  </div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script type="text/javascript">
	$("#biaoqian").addClass("active");
	//var url = "/tags?";
	//$(".pagination2").pagination("${tag.pageNumber}","${tag.totalPage}",10);
	 var count = ${tag.totalRow};//数据总量
	 var limit = ${tag.pageSize};//每页显示的条数
	 var url = "/tags?p=";//url
	 //console.log(count);
	 //console.log(url);
	 function page(){
     var page = location.search.match(/p=(\d+)/);  
     return page ? page[1] : 1;  
 	 }
 	 var p = page();//当前页数
	 paginate(count,limit,p,url);
</script>
</body>
</html>