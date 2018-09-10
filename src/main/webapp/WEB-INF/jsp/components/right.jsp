<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<!-- <title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
引入 Bootstrap
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css"> -->
</head>
<body>
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
                <h3 style="margin-top: 0">${user.userName}</h3>
                <i>${user.signature}</i>
              </div>
              <c:if test="${user.userName != user2.userName && user2 != null}">
              <div class="opt-box d-flex justify-content-center flex-column media-body">
            <span class="csdn-tracking-statistics tracking-click" data-mod="popu_379">
                                <a class="btn btn-sm btn-red-hollow" target="_self" onclick="if (confirm('确认要开始关注 ${user.userName}？')) { location.href = '/follow/${user.userId}'; }">关注</a>
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
            <dt>积分：</dt>
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
</body>
</html>