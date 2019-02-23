<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Roothub-${user.userName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
		<!-- 小屏幕显示 -->
		            <%-- <div class="col-md-3 hidden-md hidden-lg">
                <div class="panel panel-default">
                    <div class="panel-body" style="border-bottom: 1px solid #e3e3e3;">
                        <div class="media">
                            <div class="media-left">
                                <img src="/resources/images/${user.avatar}" class="avatar-lg img-circle"
                                alt="">
                            </div>
                            <div class="media-body">
                                <h3 style="margin-top: 0" title="${user.userId}" id="user_id" class= "user_id">${user.userName}</h3>
                                
                                <i>${user.signature}</i>
                            </div>
                            <c:if test="${user.userName != user2.userName && user2 != null}">
                            <div class="opt-box d-flex justify-content-center flex-column media-body">
                                <span class="csdn-tracking-statistics tracking-click" data-mod="popu_379">
                                    <a href="javascript:void(0);" class="btn btn-sm btn-red-hollow" target="_self" onclick="save()" id="followa"></a>
                                </span>
                            </div>
                        </c:if>
                    </div>
                </div>
                
                <div class="data-info d-flex item-tiling">
                    <dl class="text-center" title="${countTopic}">
                        <dt><a href="javascript:void(0);" onclick="topicList()">主题</a></dt>
                        <dd><span class="count">${countTopic}</span></dd>
                    </dl>
                    <dl class="text-center" title="${countReply}">
                        <dt><a href="javascript:void(0);" onclick="replyList()">评论</a></dt>
                        <dd><span class="count">${countReply}</span></dd>
                    </dl>
                    <dl class="text-center" title="${user.userName}的关注" id="follow_title">
                        <dt><a href="javascript:void(0);" onclick="followList()">关注</a></dt>
                        <dd><span class="follow_count_for"></span></dd>
                    </dl>
                    <dl class="text-center" title="4" id="fan_title">
                        <dt><a href="javascript:void(0);" onclick="fansList()">粉丝</a></dt>
                        <dd><span id="fan" class="follow_count_to"></span></dd>
                    </dl>
                </div>

                <div class="grade-box clearfix" style="display: flex !important;padding-left: 24px;">
                    <dl>
                        <dt>积分：</dt>
                        <dd>${countScore}</dd>
                    </dl>
                    <dl>
                        <dt title="${user.userName}的收藏"><a href="javascript:void(0);" onclick="collectList()">收藏：</a></dt>
                        <dd title="${countCollect}">${countCollect}</dd>
                    </dl>
                    
                    
                </div>
                
                
                <div class="grade-box clearfix" style="display: flex !important;padding-top: 0px;padding-left: 24px;">
                    
                    <dl>
                        <dt>访问：</dt>
                        <dd title="360">${countVisit}</dd>
                    </dl>
                    <dl title="${user.userName}加入roothub的时间">
                        <dt>入驻：</dt>
                        <dd><fmt:formatDate type="date" value="${user.createDate}" /></dd>
                    </dl>
                </div>
                
                
            </div>
        </div> --%>
        <!-- 小屏幕显示 -->
			<div class="col-md-9">
			<div class="box">
    <div class="cell">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tbody><tr>
            <td width="73" valign="top" align="center">
            <img src="${user.avatar}"  border="0" align="default" style="border-radius: 4px;" width="73" height="73px"/>
            <div class="sep10"></div>
            </td>
            <td width="10"></td>
            <td width="auto" valign="top" align="left">
            <c:if test="${user2 != null && user2.userId != user.userId}">
            	<div class="fr">
                    <button class="btn btn-follow" onclick="save()" id="follow">加入特别关注</button>   
                    <div class="sep10"></div>
                    <button class="btn btn-warning">Block</button>
                </div>
            </c:if>
                <h1 title="${user.userId}" id="user_id" class= "user_id">${user.userName}</h1>
                <span class="gray" style="font-size: 14px;">Roothub 第 ${user.userId} 号会员，加入于 <fmt:formatDate type="both" value="${user.createDate}" /><div class="sep5"></div>
                </span> 
            </td>
        </tr>
    </tbody></table>
    <div class="sep5"></div>
</div>
</div>
<div class="sep20"></div>
				<div class="panel panel-default">
					<%-- <div class="panel-heading">${user.userName}创建的话题</div> --%>
					<div class="cell_tabs"><div class="fl"><img src="${user.avatar}" width="24" style="border-radius: 24px; margin-top: -2px;" border="0"></div>
					<!-- <a href="javascript:void(0);" onclick="activitiesList()" class="cell_tab_current">动态</a> -->
					<a href="javascript:void(0);" onclick="topicList()" class="cell_tab_current" >主题</a>
					<a href="javascript:void(0);" onclick="replyList()" class="cell_tab">评论</a>
					<a href="javascript:void(0);" onclick="collectList()" class="cell_tab">收藏</a>
					<a href="javascript:void(0);" onclick="followList()" class="cell_tab">关注</a>
					<a href="javascript:void(0);" onclick="fansList()" class="cell_tab">粉丝</a>
					<a href="javascript:void(0);" onclick="topicQnaList()" class="cell_tab">提问</a></div>
					<div class="itemList"></div>	
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
			<%-- <div class="col-md-3 hidden-sm hidden-xs">
				<div class="panel panel-default">
					<div class="panel-body" style="border-bottom: 1px solid #e3e3e3;">
						<div class="media">
							<div class="media-left">
								<img src="/resources/images/${user.avatar}" class="avatar-lg img-circle"
									alt="">
							</div>
							<div class="media-body">
								<h3 style="margin-top: 0" title="${user.userId}" id="user_id" class= "user_id">${user.userName}</h3>
								
								<i>${user.signature}</i>
							</div>
							<c:if test="${user.userName != user2.userName && user2 != null}">
							<div class="opt-box d-flex justify-content-center flex-column media-body">
            						<span class="csdn-tracking-statistics tracking-click" data-mod="popu_379">
                                <a href="javascript:void(0);" class="btn btn-sm btn-red-hollow" target="_self" onclick="save()" id="followa"></a>
                            </span>
                    </div>
							</c:if>
						</div>
					</div>
					
					<div class="data-info d-flex item-tiling">
    <dl class="text-center" title="${countTopic}">
        <dt><a href="javascript:void(0);" onclick="topicList()">主题</a></dt>
        <dd><span class="count">${countTopic}</span></dd>
    </dl>
    <dl class="text-center" title="${countReply}">
        <dt><a href="javascript:void(0);" onclick="replyList()">评论</a></dt>
        <dd><span class="count">${countReply}</span></dd>
    </dl>
    <dl class="text-center" title="${user.userName}的关注" id="follow_title">
        <dt><a href="javascript:void(0);" onclick="followList()">关注</a></dt>
        <dd><span class="follow_count_for">1</span></dd>
    </dl>
    <dl class="text-center" title="${user.userName}的粉丝" id="fan_title">
        <dt><a href="javascript:void(0);" onclick="fansList()">粉丝</a></dt>
        <dd><span id="fan" class="follow_count_to"></span></dd>
    </dl>
</div>

<div class="grade-box clearfix" style="display: flex !important;padding-left: 24px;">
        <dl>
            <dt>积分：</dt>
            <dd>${countScore}</dd>
        </dl>
        <dl>
            <dt title="${user.userName}的收藏"><a href="javascript:void(0);" onclick="collectList()">收藏：</a></dt>
            <dd title="${countCollect}">${countCollect}</dd>
        </dl>
        
        
    </div>
    
    
    <div class="grade-box clearfix" style="display: flex !important;padding-top: 0px;padding-left: 24px;">
        
        <dl>
            <dt>访问：</dt>
            <dd title="360">${countVisit}</dd>
        </dl>
        <dl title="${user.userName}加入roothub的时间">
            <dt>入驻：</dt>
            <dd><fmt:formatDate type="date" value="${user.createDate}" /></dd>
        </dl>
    </div>
    
					
				</div>
			</div> --%>
			<!-- 大屏幕显示 -->
		</div>
	</div>
	<div id="back2Top" class="backTop___6Q-ki" style="display:none">
		<div class="line___F1WY0"></div>
		<div class="arrow___3UCwo"></div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/goTop.js"></script>
	<script src="/resources/layui/layui.js"></script>
	<script src="/resources/layui/layui-paginate.js"></script>
	<script src="/resources/js/user/detail.js"></script>
	<script src="/resources/js/formatDate.js"></script>
</body>
</html>