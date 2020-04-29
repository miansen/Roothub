<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Roothub-${nodeName}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
 <div class="wrapper">
  <jsp:include page="components/head.jsp"></jsp:include>
    <div class="row">
      <div class="col-md-9">
        <div class="box box-primary">
        	<div class="box-header with-border">
	        	<ul class="nav nav-pills" id="tab">
		        	<li class="${tab eq 'all' ? 'all active' : 'all'}"><a href="/?tab=all&node=${nodeName}">全部</a></li>
		        	<li class="${tab eq 'hot' ? 'hot active' : 'hot'}"><a href="/?tab=hot&node=${nodeName}">最热</a></li>
		        	<li class="${tab eq 'new' ? 'new active' : 'new'}"><a href="/?tab=new&node=${nodeName}">最新</a></li>
		        	<li class="${tab eq 'lonely' ? 'lonely active' : 'lonely'}"><a href="/?tab=lonely&node=${nodeName}">无人问津</a></li>
	        	</ul>
    		</div>
	        <div class="box-body">
	          <c:forEach var="item" items="${page.list}" varStatus="status">
		          <div class="media">
			          <c:if test="${fn:length(item.avatar) > 0}">
			            <div class="media-left">
			              <img src="${item.avatar}" class="avatar img-circle" alt="">
			            </div>
			           </c:if>
		            <div class="media-body">
		              <div class="title">
		                <c:choose>
		                	<c:when test="${item.url != null}">
		                		<a href="${item.url}" target="_blank">${item.title}</a>
		                	</c:when>
		                	<c:otherwise>
		                		<a href="/topic/${item.topicId}">${item.title}</a>
		                	</c:otherwise>
		                </c:choose>
		              </div>
		              <div class="tip">
		              <p class="gray">
		              <c:if test="${item.top}">
					  <span class="label label-primary">置顶</span> <span>•</span>
					  </c:if>
					  <c:if test="${item.good}">
					  <span class="label label-success">精华</span> <span>•</span>
					  </c:if>
					  
					  <c:if test="${not empty item.nodeTitle}">
					  	<span><a href="/n/${item.nodeTitle}" class="node">${item.nodeTitle}</a></span>
					    <span>•</span>
					  </c:if>
					    
		                <a href="/user/${item.author}">${item.author}</a>
		                <c:if test="${item.viewCount > 0}">
		                	<span class="hidden-sm hidden-xs">•</span>
		                	<span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
		                </c:if>
		                
		                <!-- 评论数量 -->
		                <c:if test="${item.replyCount > 0}">
		                	<span class="hidden-sm hidden-xs">•</span>
		                	<span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
		                </c:if>
		                
		                <span>•</span>
		                <span><fmt:formatDate type="date" value="${item.createDate}" /></span>
		                </p>
		                </div>
		              </div>
		            <div class="media-right"><span class="badge badge-default"><a href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
		            <c:if test="${!status.last}">
		            	<div class="divide mar-top-5"></div>
		            </c:if>
		          </div>
	          </c:forEach>
	      </div>
      	    <div class="box-footer panel-footer" id="paginate"></div>
        </div>
      </div>
   <div class="col-md-3 hidden-sm hidden-xs">
  	<c:if test="${sessionScope.user != null}">
					<div class="box box-primary" id="session">
						<div class="box-body">
							<div class="media">
								<div class="media-left">
									<a href="/user/${sessionScope.user.userName}">
										<img src="${sessionScope.user.avatar}" title="" class="user-avatar img-circle">
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
									<a href="/topic/create" style="font-size: 14px;">
										<button class="btn btn-primary">发布帖子</button>
									</a>
								</div>
							</div>
							<div class="sep10" style="height: 10px;"></div>
							<table cellpadding="0" cellspacing="0" border="0" width="100%"
								class="table_fade" style="font-size: 14px;">
								<tbody>
									<tr>
										<td width="33%" align="center"><a href="/user/topics"
											class="dark" style="display: block;"><span class="bigger">${countTopic}</span>
												<div class="sep3"></div> <span class="fade">我的主题</span></a></td>
										<td width="34%"
											style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);"
											align="center"><a href="/collect/topics" class="dark"
											style="display: block;"><span class="bigger">${countCollect}</span>
												<div class="sep3"></div> <span class="fade">我的收藏</span></a></td>
										<td width="33%" align="center"><a href="/follow/topics"
											class="dark" style="display: block;"><span class="bigger">${countFollow}</span>
												<div class="sep3"></div> <span class="fade">特别关注</span></a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer" style="background-color: white">
							<div class="row">
								<span class="col-md-6"><a href="/notification/list"><span
										id="n_count">${countNotReadNotice}</span> 条未读消息</a></span> <span class="col-md-6 text-right">积分：<a
									href="/top100">${countScore}</a></span>
							</div>
						</div>
					</div>
				</c:if>
				
	<!-- 今日热议帖子 -->
    <div class="box box-primary">
      <div class="box-header with-border"><b>今日热门</b></div>
      <div class="box-body">
      	<table class="table" style="font-size: 14px;">
        <tbody>
	        <c:forEach var="item" items="${hotTopicList}">
	          <tr>
	          <c:if test="${fn:length(item.avatar) > 0}">
		         <td width="24" valign="middle" align="center">
		              <img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;">
		          </td>
              </c:if>
	            <td>
	            	<c:choose>
	                	<c:when test="${item.url != null}">
	                		<a href="${item.url}">${item.title}</a>
	                	</c:when>
	                	<c:otherwise>
	                		<a href="/topic/${item.topicId}">${item.title}</a>
	                	</c:otherwise>
	                </c:choose>
	            </td>
	          </tr>
	          </c:forEach>
          </tbody>
      	</table>
      </div>
    </div>
    
    <!-- 今日等待回复的主题 -->
    <div class="box box-primary">
      <div class="box-header with-border"><b>等待回复</b></div>
      <div class="box-body">
      	<table class="table" style="font-size: 14px;">
        	<tbody>
		        <c:forEach var="item" items="${noReplyTopicList}">
		          <tr>
		          <td width="24" valign="middle" align="center">
		                <a href="/user/${item.author}"><img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;"></a>
		            </td>
		            <td>
		            	<c:choose>
		                	<c:when test="${item.url != null}">
		                		<a href="${item.url}">${item.title}</a>
		                	</c:when>
		                	<c:otherwise>
		                		<a href="/topic/${item.topicId}">${item.title}</a>
		                	</c:otherwise>
		                </c:choose>
		            </td>
		          </tr>
		         </c:forEach>
        	</tbody>
      	</table>
      </div>
    </div>
    
    <!-- 积分榜 -->
    <div class="box box-primary">
    <div class="box-header with-border"><b>积分榜  <a class="dark" href="/top100">TOP 100 &gt;&gt;</a></b></div>
    <div class="box-body">
      <div class="row">
      	<div class="inner top100">
      		<ol>
      			<c:forEach var="item" items="${scoreList}">
      				<li>
      					<span class="top_score">${item.score}</span>
      					<span class="user_name">
      						<a href="/user/${item.userName}">${item.userName}</a>
      					</span>
      				</li>
      			</c:forEach>
      		</ol>
      	</div>
      </div>
    </div>
  </div>
  
  	<!-- 热门板块 -->
    <div class="box box-primary">
    	<div class="box-header with-border"><b>热门板块</b></div>
	    <div class="box-body">
	      <div class="row">
	      	 <c:forEach var="item" items="${hotNodeList}">
		          <div class="col-md-4" style="margin-bottom: 10px; padding-left: 10px;">
		            <a href="${item.url}"><span class="label label-primary text-muted">${item.nodeTitle}</span></a>
		            <small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
		          </div>
	          </c:forEach>
	      </div>
	    </div>
   	 </div>
  
    <!-- 社区运行状况 -->
    <div class="box box-primary">
    	<div class="box-header with-border"><b>社区运行状况</b></div>
	    <div class="box-body">
	    	<p>注册会员：${countUserAll}</p>
	    	<p>帖子：${countAllTopic}</p>
	    	<p>回复：${countAllReply}</p>
	    </div>
  	</div>
  </div>
    </div>
    </div>
</div>
<div id="back2Top" class="backTop___6Q-ki" style="display:none">
	<div class="line___F1WY0"></div>
	<div class="arrow___3UCwo"></div>
</div>
<jsp:include page="components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/index.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<!-- <script src="/resources/js/login_info.js"></script> -->
<script src="/resources/js/formatDate.js"></script>
<script type="text/javascript">
//var tab = "${tab}";//板块
var tab = "${tab}";//父板块
var nodeName = "${nodeName}";
//var url = "/?tab="+tab+"&ptab="+ptab+"&"
//$(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);
 var count = ${page.totalRow};//数据总量
 var limit = ${page.pageSize};//每页显示的条数
 var url = "?node="+nodeName+"&tab="+tab+"&p=";//url
 function page(){
     var page = location.search.match(/p=(\d+)/);  
     return page ? page[1] : 1;  
 }
 var p = page();//当前页数
 //console.log("p:"+p);
 //console.log(count);
 //console.log(url);
 paginate(count,limit,p,url);
</script>
</body>
</html>