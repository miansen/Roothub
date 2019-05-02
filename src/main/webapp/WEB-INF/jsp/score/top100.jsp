<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>top100-Roothub</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="/">主页</a> / Top100积分榜
					</div>
					<div class="panel-body" style="color: #333;">
						<div class="panel">
							<div class="inner"></div>
						</div>
					</div>
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
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/score/top100.js"></script>
	<script src="/resources/js/login_info.js"></script>
	<script src="/resources/js/goTop.js"></script>
</body>
</html>