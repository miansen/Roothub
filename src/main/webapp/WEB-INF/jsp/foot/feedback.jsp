<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>问题反馈-Roothub</title>
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
						<a href="/">主页</a> / 问题反馈
					</div>
					<div class="panel-body" style="color: #333;">
					<h5>hello，网站还不够完美，需要改进的地方很多，欢迎您的留言。</h5>
					<h5>您宝贵的建议对我来说至关重要，谢谢！</h5>
					<form id="feedback_add" enctype="multipart/form-data" method="post"
						action="/feedback/add">
						<div class="form-group">
							<textarea name="info" id="info" class="form-control" rows="3"></textarea>
						</div>
						<button type="button" class="btn btn-primary" id="feedback_add_btn">提交</button>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/feedback.js"></script>
</body>
</html>