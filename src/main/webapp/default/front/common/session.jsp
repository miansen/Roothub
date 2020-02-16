<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- 当前登录信息 -->
<div class="panel panel-default" id="session">
	<c:choose>
		<c:when test="${sessionScope.user == null || empty sessionScope.user}">
			<div class="panel-body" id="nologin">
				<p>嗨~听说最近互联网上有很多趣事哦，愿意跟我们分享一下吗？</p>
				<p>
					<a href="/post/add" style="font-size: 14px;"><button
							class="btn btn-success">发布帖子</button></a>
				</p>
			</div>
		</c:when>
		<c:otherwise>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<a href="/user/${sessionScope.user.userName}"> <img
							src="${sessionScope.user.avatar}" title=""
							class="user-avatar img-circle">
						</a>
					</div>
					<div class="media-body">
						<div class="media-heading">
							<strong><a href="/user/${sessionScope.user.userName}">${sessionScope.user.userName}</a></strong>
							<div style="color: #7A7A7A; font-size: 12px; margin-top: 5px;">
								<i>${sessionScope.user.signature}</i>
							</div>
						</div>
					</div>
					<div style="margin-top: 15px;">
						<a href="/post/add" style="font-size: 14px;"><button
								class="btn btn-success">发布帖子</button></a>
					</div>
				</div>
				<div class="sep10" style="height: 10px;"></div>
				<table cellpadding="0" cellspacing="0" border="0" width="100%"
					class="table_fade" style="font-size: 14px;">
					<tbody>
						<tr>
							<td width="33%" align="center"><a href="/user/topics"
								class="dark" style="display: block;"><span class="bigger">0</span>
									<div class="sep3"></div> <span class="fade">我的帖子</span></a></td>
							<td width="34%"
								style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);"
								align="center"><a href="/collect/topics" class="dark"
								style="display: block;"><span class="bigger">0</span>
									<div class="sep3"></div> <span class="fade">我的收藏</span></a></td>
							<td width="33%" align="center"><a href="/follow/topics"
								class="dark" style="display: block;"><span class="bigger">0</span>
									<div class="sep3"></div> <span class="fade">特别关注</span></a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer" style="background-color: white">
				<div class="row">
					<span class="col-md-6"><a href="/notification/list"><span
							id="n_count">0</span> 条未读消息</a></span> <span class="col-md-6 text-right">积分：<a
						href="/top100">${sessionScope.user.score}</a></span>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
