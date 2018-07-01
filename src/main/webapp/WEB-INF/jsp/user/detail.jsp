<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>${user.userName}的个人主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
		<!-- 小屏幕显示 -->
		            <div class="col-md-3 hidden-md hidden-lg">
                <div class="panel panel-default">
                    <div class="panel-body" style="border-bottom: 1px solid #e3e3e3;">
                        <div class="media">
                            <div class="media-left">
                                <img src="/resources/images/${user.avatar}" class="avatar-lg img-circle"
                                alt="">
                            </div>
                            <div class="media-body">
                                <h3 style="margin-top: 0" title="${user.userId}" id="user_id">${user.userName}</h3>
                                
                                <i>${user.signature}</i>
                            </div>
                            <c:if test="${user.userName != user2.userName && user2 != null}">
                            <div class="opt-box d-flex justify-content-center flex-column media-body">
                                <span class="csdn-tracking-statistics tracking-click" data-mod="popu_379">
                                    <a href="javascript:void(0);" class="btn btn-sm btn-red-hollow" target="_self" onclick="save()"></a>
                                </span>
                            </div>
                        </c:if>
                    </div>
                </div>
                
                <div class="data-info d-flex item-tiling">
                    <dl class="text-center" title="${countTopic}">
                        <dt><a href="/user/${user.userName}/topics">主题</a></dt>
                        <dd><a href="/user/${user.userName}/topics"><span class="count">${countTopic}</span></a></dd>
                    </dl>
                    <dl class="text-center" title="${countReply}">
                        <dt><a href="/user/${user.userName}/replys">评论</a></dt>
                        <dd><a href="/user/${user.userName}/replys"><span class="count">${countReply}</span></a></dd>
                    </dl>
                    <dl class="text-center" title="4">
                        <dt>粉丝</dt>
                        <dd><span class="count" id="fan">4</span></dd>
                    </dl>
                    <dl class="text-center" title="18">
                        <dt>关注</dt>
                        <dd><span class="count">18</span></dd>
                    </dl>
                </div>

                <div class="grade-box clearfix" style="display: flex !important;">
                    <dl>
                        <dt>声望：</dt>
                        <dd>10</dd>
                    </dl>
                    <dl>
                        <dt><a href="/collect/topics">收藏：</a></dt>
                        <dd title="${countCollect}">${countCollect}</dd>
                    </dl>
                    
                    
                </div>
                
                
                <div class="grade-box clearfix" style="display: flex !important;padding-top: 0px;">
                    
                    <dl>
                        <dt>访问：</dt>
                        <dd title="360">13</dd>
                    </dl>
                    <dl title="228945">
                        <dt>入驻：</dt>
                        <dd><fmt:formatDate type="date" value="${user.createDate}" /></dd>
                    </dl>
                </div>
                
                
            </div>
        </div>
        <!-- 小屏幕显示 -->
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">${user.userName}创建的话题</div>
					<c:forEach var="item" items="${topicPage.list}">
						<div class="panel-body paginate-bot"
							style="border-bottom: 1px solid #e2e2e2;">
							<div class="media">
								<div class="media-body">
									<div class="title">
										<a href="/topic/${item.topicId}"> ${item.title} </a>
									</div>
									<p>
										<span><a href="/user/${item.author}">${item.author}</a></span>
										<span class="hidden-sm hidden-xs">•</span> <span
											class="hidden-sm hidden-xs"><a
											href="/topic/${item.topicId}">${item.replyCount}个评论</a></span> <span
											class="hidden-sm hidden-xs">•</span> <span
											class="hidden-sm hidden-xs">${item.viewCount}次点击</span> <span>•</span>
										<span><fmt:formatDate type="both" dateStyle="medium"
												timeStyle="short" value="${item.createDate}" /></span>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="panel-footer">
						<a href="/user/${user.userName}/topics">${user.userName}的更多话题&gt;&gt;</a>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">${user.userName}评论的话题</div>
					<table class="table table-striped">
						<tbody>
							<c:forEach var="item" items="${replyPage.list}">
								<tr>
									<td><fmt:formatDate type="both" dateStyle="medium"
											timeStyle="short" value="${item.createDate}" /> 评论了 <a
										href="/user/${item.author}">${item.author}</a> 创建的话题 › <a
										href="/topic/${item.topicId}">${item.title}</a></td>
								</tr>
								<tr class="user_comments">
									<td class="show_big_image">${item.replyContent}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="panel-footer">
						<a href="/user/${user.userName}/replys">${user.userName}的更多评论&gt;&gt;</a>
					</div>
				</div>
				<button id="toggleBigImageBtn" data-toggle="modal" class="hidden"
					data-target="#showBigImageModal"></button>
				<div class="modal fade" tabindex="-1" role="dialog"
					id="showBigImageModal">
					<div class="modal-dialog" style="width: 98%" role="document">
						<div class="modal-content">
							<div class="modal-body text-center">
								<img src="" id="bigImage" style="max-width: 100%;" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 大屏幕显示 -->
			<div class="col-md-3 hidden-sm hidden-xs">
				<div class="panel panel-default">
					<div class="panel-body" style="border-bottom: 1px solid #e3e3e3;">
						<div class="media">
							<div class="media-left">
								<img src="/resources/images/${user.avatar}" class="avatar-lg img-circle"
									alt="">
							</div>
							<div class="media-body">
								<h3 style="margin-top: 0" title="${user.userId}" id="user_id">${user.userName}</h3>
								
								<i>${user.signature}</i>
					<%-- 			<div class="cell" style="border-bottom: 0px solid #e2e2e2;">
									<table cellpadding="5" cellspacing="0" border="0" width="100%">
										<tbody style="font-size: 14px;">
											<c:if test="${user.userName != user2.userName && user2 != null}">
												<tr>
													<td width="80" align="left"><span class="gray"><input
															type="button" value="加入特别关注"
															onclick="if (confirm('确认要开始关注 ${user.userName}？')) { location.href = '/follow/${user.userId}'; }"
															class="btn btn-success"></span></td>
												</tr>
											</c:if>
											<tr>
												<td width="80" align="left"><span class="gray">入驻时间：<strong
														style="color: #333"><fmt:formatDate type="date"
																value="${user.createDate}" /></strong></span></td>
											</tr>
											<tr>
												<td width="80" align="left"><span class="gray">收藏话题：<a
														href="/user/public/collects">0</a></span></td>
											</tr>
											<tr>
												<td width="80" align="left"><span class="gray">声望：<strong
														style="color: #333">12</strong></span></td>
											</tr>
											<tr>
												<td width="80" align="left"><span class="gray">主题：<strong
														style="color: #333">12</strong></span></td>
											</tr>
											<tr>
												<td width="80" align="left" style="font-size: 14px;"><span
													class="gray">评论：<strong style="color: #333">12</strong></span></td>
											</tr>
											<tr>
												<td width="80" align="left"><span class="gray">粉丝：<strong
														style="color: #333">12</strong></span></td>
											</tr>
											<tr>
												<td width="80" align="left"><span class="gray">关注：<strong
														style="color: #333">12</strong></span></td>
											</tr>
										</tbody>
									</table>
								</div> --%>
							</div>
							<c:if test="${user.userName != user2.userName && user2 != null}">
							<div class="opt-box d-flex justify-content-center flex-column media-body">
            <span class="csdn-tracking-statistics tracking-click" data-mod="popu_379">
                                <a href="javascript:void(0);" class="btn btn-sm btn-red-hollow" target="_self" onclick="save()"></a>
                            </span>
                    </div>
							</c:if>
						</div>
					</div>
					
					<div class="data-info d-flex item-tiling">
    <dl class="text-center" title="${countTopic}">
        <dt><a href="/user/${user.userName}/topics">主题</a></dt>
        <dd><a href="/user/${user.userName}/topics"><span class="count">${countTopic}</span></a></dd>
    </dl>
    <dl class="text-center" title="${countReply}">
        <dt><a href="/user/${user.userName}/replys">评论</a></dt>
        <dd><a href="/user/${user.userName}/replys"><span class="count">${countReply}</span></a></dd>
    </dl>
    <dl class="text-center" title="4">
        <dt>粉丝</dt>
        <dd><span class="count" id="fan">4</span></dd>
    </dl>
    <dl class="text-center" title="18">
        <dt>关注</dt>
        <dd><span class="count">18</span></dd>
    </dl>
</div>

<div class="grade-box clearfix" style="display: flex !important;">
        <dl>
            <dt>声望：</dt>
            <dd>10</dd>
        </dl>
        <dl>
            <dt><a href="/collect/topics">收藏：</a></dt>
            <dd title="${countCollect}">${countCollect}</dd>
        </dl>
        
        
    </div>
    
    
    <div class="grade-box clearfix" style="display: flex !important;padding-top: 0px;">
        
        <dl>
            <dt>访问：</dt>
            <dd title="360">13</dd>
        </dl>
        <dl title="228945">
            <dt>入驻：</dt>
            <dd><fmt:formatDate type="date" value="${user.createDate}" /></dd>
        </dl>
    </div>
    
					
				</div>
			</div>
			<!-- 大屏幕显示 -->
		</div>
	</div>
	<div id="back2Top" class="backTop___6Q-ki" style="display:none">
		<div class="line___F1WY0"></div>
		<div class="arrow___3UCwo"></div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="/resources/js/jquery.js"></script>
	<!-- 引入 Bootstrap -->
	<script src="/resources/js/bootstrap.min.js"></script>
	<!-- 缓慢回到顶部 -->
	<script src="/resources/js/goTop.js"></script>
	<script src="/resources/js/user_detail.js"></script>
</body>
</html>