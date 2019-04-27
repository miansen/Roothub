<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>报表-Roothub</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="/">主页</a> / 报表
					</div>
					<a href="/excel/download"><button type="button" class="btn btn-success" style="margin: 10px;">下载excel</button></a>
					<div style="margin: 10px;">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">话题</a></li>
							<li role="presentation"><a href="#profile"
								aria-controls="profile" role="tab" data-toggle="tab">父板块</a></li>
							<li role="presentation"><a href="#messages"
								aria-controls="messages" role="tab" data-toggle="tab">子板块</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content" style="margin: 10px;">
							<div role="tabpanel" class="tab-pane active" id="home">
								<table class="table table-hover table-striped" id="tb">
									<thead>
										<tr>
											<th>话题标识</th>
											<th>父板块标识</th>
											<th>子版块标识</th>
											<th>话题标题</th>
											<!-- <th>话题内容标签</th>
											<th>话题内容</th>
											<th>创建时间</th>
											<th>更新时间</th>
											<th>最后回复话题时间</th>
											<th>最后回复话题的用户</th>
											<th>浏览量</th>
											<th>话题作者</th>
											<th>1置顶 0默认</th>
											<th>1精华 0默认</th>
											<th>1显示 0不显示</th>
											<th>回复数量</th>
											<th>1删除 0默认</th>
											<th>话题内容标签是否被统计过 1是 0否默认</th>
											<th>点赞</th>
											<th>踩数</th>
											<th>话题状态 1000:有效 1100:无效 1200:未生效</th>
											<th>所属节点</th>
											<th>节点名称</th>
											<th>备注</th>
											<th>话题作者头像</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${row1}" varStatus="status">
											<tr class="tr">
												<td>${item.topicId}</td>
												<td>${item.ptab}</td>
												<td>${item.tab}</td>
												<td>${item.title}</td>
												<%-- <td>${item.tag}</td>
												<td>${item.content}</td>
												<td>${item.createDate}</td>
												<td>${item.updateDate}</td>
												<td>${item.lastReplyTime}</td>
												<td>${item.lastReplyAuthor}</td>
												<td>${item.viewCount}</td>
												<td>${item.author}</td>
												<td>${item.top}</td>
												<td>${item.good}</td>
												<td>${item.showStatus}</td>
												<td>${item.replyCount}</td>
												<td>${item.isDelete}</td>
												<td>${item.tagIsCount}</td>
												<td>${item.postGoodCount}</td>
												<td>${item.postBadCount}</td>
												<td>${item.statusCd}</td>
												<td>${item.nodeSlug}</td>
												<td>${item.nodeTitle}</td>
												<td>${item.remark}</td>
												<td>${item.avatar}</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile">
								<table class="table table-hover table-striped" id="tb">
									<thead>
										<tr>
											<th>父板块标识</th>
											<th>父板块名称</th>
											<th>父板块描述</th>
											<th>创建时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${row2}" varStatus="status">
											<tr class="tr">
												<td>${item.id}</td>
												<td>${item.tabName}</td>
												<td>${item.tabDesc}</td>
												<td>${item.createDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane" id="messages">
								<table class="table table-hover table-striped" id="tb">
									<thead>
										<tr>
											<th>子板块标识</th>
											<th>子板块名称</th>
											<th>子板块标签</th>
											<th>创建时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${row3}" varStatus="status">
											<tr class="tr">
												<td>${item.sectionId}</td>
												<td>${item.sectionName}</td>
												<td>${item.sectionTab}</td>
												<td>${item.createDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

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