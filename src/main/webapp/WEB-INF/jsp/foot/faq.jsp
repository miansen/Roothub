<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>FAQ-Roothub</title>
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
						<a href="/">主页</a> / FAQ
					</div>
					<div class="panel-body" style="color: #333;">
						<h2>Roothub 是什么？</h2>
						<p>Roothub不是一个公司，也不是一个商业项目。Roothub本质上是一个学生的毕业项目，并且至今都在维护。</p>
						<h2>为什么取名 Roothub？</h2>
						<p>root指的是linux系统中的root用户，拥有整个系统的最高权限。</p>
						<p>hub是模仿github取的，可以理解为中心。</p>
						<p>合起来就是拥有最高权限的中心</p>
						<!-- <p style="color: #ccc;">哈哈，其实不是的。真实情况是：我的毕业项目叫root，然后我想部署到服务器上，就去买域名，然后域名网站就自动给我推荐了roothub.cn这个域名，看着挺顺眼的，就买下了。</p> -->
						<h2>如何联系我？</h2>
						<P>如果你发现了网站的问题，或是你的账号遇到了难以解决的问题，请发送邮件到 1158827539@qq.com 与我取得联系。我会尽可能在 24 小时内回复邮件。</P>
						<h2>Roothub是用什么写的？</h2>
						<p>用java写的</p>
						<h2>为什么不能删除自己的主题？</h2>
						<p>目前暂不支持删除功能，如果你发表的主题违规了，那么该主题就会被删除。</p>
						<h2>为什么不能删除自己的回复？</h2>
						<p>回复一旦发布，就不可以删除和编辑。这个规则的设立，是为了创造一个所有人对自己说过的话更加负责的社区氛围。</p>
						<h2>为什么不能编辑自己的主题？</h2>
						<p>后续可能会推出该功能</p>
						<h2>为什么不能编辑自己的回复？</h2>
						<p>回复在发表之后就不可以再进行任何编辑。因此，建议大家在按下发布按钮之前，仔细阅读一下以确保没有错别字或者不友善的表达方式。</p>
					</div>
				</div>
			</div>
		</div>
	</div>
  </div>
</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
</body>
</html>