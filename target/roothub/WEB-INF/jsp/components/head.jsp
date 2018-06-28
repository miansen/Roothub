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
<script src="/resources/js/logout.js"></script>
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "https://hm.baidu.com/hm.js?f0543f7ed0d9239755f96bdb3d8f732c";
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
					href="/">roothub</a>
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
					<li id="biaoqian"><a href="/tags">标签</a></li>
				
						<li id="loginli" style="display:none"><a href="/login">登录</a></li>
						<li id="zhuceli" style="display:none"><a href="/register">注册</a></li>
				
					
						<li class="hidden-md hidden-lg"><a href="/topic/create">发布话题</a>
						</li>
						<li id="loginuser" style="display:none"><a href="/user/public"> public <span
								class="badge" id="badge"></span>
						</a></li>
						<li id="shezhili" style="display:none"><a href="/user/settings/profile">设置</a></li>
						<li id="tuichuli" style="display:none"><a
							href="javascript:if(confirm('确定要登出roothub吗？'))location.href='/logout'">退出</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
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
		<script type="text/javascript">
		$(function(){
		      $.ajax({
		        type:"get",
		        url:"/session",
		        dataType:"json",
		        success:function(data){
		          //console.log(JSON.stringify(data));
		          if(data.success != null && data.success == true){
		            $("#loginuser").show();
		            $("#loginuser a").text(data.data.userName);
		            $("#loginuser a").attr("href","/user/"+data.data.userName);
		            $("#shezhili").show();
		            $("#tuichuli").show();
		          }
		          if(data.success != null && data.success == false){
		            $("#loginli").show();
		            $("#zhuceli").show();
		            $("#nologin").show();
		          }
		        },
		        error:function(data){

		        }
		      });
		    });
		</script>
</body>
</html>