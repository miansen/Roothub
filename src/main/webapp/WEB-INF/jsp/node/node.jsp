<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>节点-roothub</title>
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
  <div class="panel-heading">全部节点</div>
    <div class="panel-body">
      <div class="row">
      <c:forEach var="item" items="${nodeList}">
        <%-- <c:if test="${item.tabId == 15}"> --%>
            <div class="col-md-2" style="margin-bottom: 10px; padding-left: 10px;">
              <a href="${item.url}">
                <span class="label label-primary">${item.nodeTitle}</span>
              </a>
              <span class="text-muted"></span>
              <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
            </div>
            <%-- </c:if> --%>
          </c:forEach> 
      </div>
    </div>
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
	$("#nodes").addClass("active");
</script>
</body>
</html>