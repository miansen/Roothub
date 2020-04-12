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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css" />
<link href="/resources/css/app2.css" rel="stylesheet" type="text/css">
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?e988748e1cd0adcffabdb560cc3df84d";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body>
	<nav class="navbar navbar-default"
		style="border-radius: 0; margin-bottom: 10px;">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" style="font-weight: 700; font-size: 27px;"
					href="/">学习互助系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse header-navbar">
				<form class="navbar-form navbar-left hidden-xs hidden-sm"
					role="search" action="/search" method="get">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="s" value=""
							style="width: 270px;" placeholder="回车搜索">
						<!-- <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span> -->
					</div>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden-xs" id="shouye"><a href="/">首页</a></li>
					<li id="nodes"><a href="/nodes">板块</a></li>
					<!-- <li id="biaoqian"><a href="/tags">标签</a></li> -->
					<c:choose>
                	<c:when test="${sessionScope.user != null}">
                		<li id="loginuser">
                			<a href="/user/${sessionScope.user.userName}"><span class="badge" id="badge">${sessionScope.user.userName}</span></a>
                		</li>
						<li id="shezhili">
							<a href="/user/settings/profile">设置</a>
						</li>
						<li id="tuichuli">
							<a href="javascript:if(confirm('确定要登出Roothub吗？'))location.href='/logout'">退出</a>
						</li>
                	</c:when>
                	<c:otherwise>
                		<li id="loginli"><a href="/login">登录</a></li>
						<li id="zhuceli"><a href="/register">注册</a></li>		
						<!-- <li class="hidden-md hidden-lg"><a href="/topic/create">发布话题</a></li> -->
                	</c:otherwise>
                </c:choose>
				</ul>
			</div>
		</div>
	</nav>
	
	<div id="tab-nav">
		<div class="container" style="height: 45px;">
			<ul style="padding-left: 0px">
				<li class="li-cate"><a href="/" class="${statusCd eq 9999 ? "li-cate-active" : "li-cate-a"}">首页</a></li>
				<li class="li-cate"><a href="/?statusCd=1000" class="${statusCd eq 1000 ? "li-cate-active" : "li-cate-a"}">理智讨论区</a></li>
				<li class="li-cate"><a href="/?statusCd=1100" class="${statusCd eq 1100 ? "li-cate-active" : "li-cate-a"}">学习动态</a></li>
				<li class="li-cate"><a href="/?statusCd=1200" class="${statusCd eq 1200 ? "li-cate-active" : "li-cate-a"}">教学动态</a></li>
				<li class="li-cate"><a href="/?statusCd=1300" class="${statusCd eq 1300 ? "li-cate-active" : "li-cate-a"}">招募管理</a></li>
				<li class="li-cate"><a href="/?statusCd=1400" class="${statusCd eq 1400 ? "li-cate-active" : "li-cate-a"}">共同兴趣</a></li>
			    <div class="clear"></div>
			</ul>
		</div>
		</div>
		
	<div class="container" style="padding: 0 25px;">
		<form class="hidden-lg hidden-md" style="margin: 0 -10px;"
			role="search" action="/search" method="get">
			<div class="form-group has-feedback" style="margin-bottom: 10px;">
				<input type="text" class="form-control" name="q" value=""
					placeholder="回车搜索">
				<!-- <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span> -->
			</div>
		</form>
		<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
		<script src="/resources/js/jquery.js"></script>
		<!-- 引入 Bootstrap -->
		<script src="/resources/js/bootstrap.min.js"></script>
		<!-- <script src="/resources/js/head.js"></script> -->
</body>
</html>