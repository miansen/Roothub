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
<div class="col-md-3 hidden-sm hidden-xs">
  	<div class="panel panel-default" id="session">
       <div class="panel-body">
              <div class="media">
                <div class="media-left">
                  <a href="/user/${user.userName}">
                    <img src="/resources/images/${user.avatar}" title="" class="avatar img-circle">
                  </a>
                </div>
                <div class="media-body">
                  <div class="media-heading">
                    <strong><a href="/user/${user.userName}">${user.userName}</a></strong>
                    <div style="color: #7A7A7A; font-size: 12px; margin-top:5px;">
                      <i>${user.signature}</i>
                    </div>
                  </div>
                </div>
                <div style="margin-top: 15px;">
                  <a href="/topic/create"><button class="btn btn-success">发布话题</button></a>
                </div>
              </div>
              <div class="sep10" style="height: 10px;"></div>
		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table_fade" style="font-size: 14px;">
    		<tbody><tr>
        		<td width="33%" align="center"><a href="/user/topics" class="dark" style="display: block;"><span class="bigger">${countTopicByUserName}</span><div class="sep3"></div><span class="fade">我的主题</span></a></td>
        		<td width="34%" style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);" align="center"><a href="/collect/topics" class="dark" style="display: block;"><span class="bigger">${countCollect}</span><div class="sep3"></div><span class="fade">我的收藏</span></a></td>
        		<td width="33%" align="center"><a href="/follow/topics" class="dark" style="display: block;"><span class="bigger">2</span><div class="sep3"></div><span class="fade">特别关注</span></a></td>
    		</tr>
		</tbody></table>
            </div>
            <div class="panel-footer" style="background-color: white">
              <div class="row">
                <span class="col-md-6"><a href="/notification/list"><span id="n_count">${notReadNotice}</span> 条未读消息</a></span>
                <span class="col-md-6 text-right">积分：<a href="/top100">0</a></span>
              </div>
            </div>
    </div>
  </div>
</body>
</html>