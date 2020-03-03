<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/classes/ui/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>选择父权限</title>
    <link rel="icon" href="${contextPath}/admin/resource/images/favicon.ico">
    <link rel="stylesheet" href="${contextPath}/admin/resource/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/skins/_all-skins.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <link rel="stylesheet" href="${contextPath}/admin/resource/css/admin.css">
    <link rel="stylesheet" href="${contextPath}/admin/resource/wangEditor/wangEditor.min.css">
    <link rel="stylesheet" href="${contextPath}/admin/resource/cropper/cropper.css">
    <style>
        @media screen and (max-width: 768px) {
            .content-header {
                position: relative;
                padding: 65px 15px 0 15px;
            }
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
    <script src="${contextPath}/admin/resource/js/app.min.js"></script>
    <script src="${contextPath}/admin/resource/layui/layui.js"></script>
    <script src="${contextPath}/admin/resource/js/avatar.js"></script>
    <script src="${contextPath}/admin/resource/cropper/cropper.min.js"></script>

    <script>
        function toast(txt, icon) {
            $.toast({
                text: txt, // Text that is to be shown in the toast
                heading: '系统提醒', // Optional heading to be shown on the toast
                icon: icon || 'error', // Type of toast icon warning, info, success, error
                showHideTransition: 'slide', // fade, slide or plain
                allowToastClose: true, // Boolean value true or false
                hideAfter: 2000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
                stack: false, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
                position: 'top-right', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
            });
        }
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background-color: #ecf0f5;">
<div class="content-wrapper">
    <section class="content">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">父节点列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <form action="${contextPath}/admin/permission/list/parent" method="get" class="form-inline">
                    <div class="form-group" style="margin-bottom: 10px;">
                        <input type="text" name="permissionName" value="${permissionName}" class="form-control" placeholder="权限名" style="display: inline-block;width: 50%;">
                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
                    </div>
                </form>
                <c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<table class="table table-bordered">
                    			<thead>
                    				<tr>
                        				<th>权限名称</th>
                        				<th>权限描述</th>
                        				<th>选择</th>
                    				</tr>
                    			</thead>
                    		<tbody>
                    		<c:forEach var="permissionVO" items="${page.list}">
                    			<tr>
                            		<td>${permissionVO.permissionName}</td>
                            		<td>${permissionVO.permissionDesc}</td>
                            		<td><button class="btn btn-primary btn-sm" type="button" onclick="toParent('${permissionVO.permissionId}','${permissionVO.permissionName}')">选择</button></td>
                        		</tr>
                    		</c:forEach>
                    		</tbody>
                		</table>
                		<common:paginate page="${page}" class="panel-footer" style="padding: 0px 18px;" />
                	 </c:when>
                     <c:otherwise>
                        <div class="empty-data">
                        	<img src="${contextPath}/admin/resource/images/no-data.jpg" style="width: 100%;">
                        </div>
                     </c:otherwise>
               </c:choose>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        function toParent(id, name) {
        	var index = parent.layer.getFrameIndex(window.name);
            parent.$('#parentPermissionId').val(id);
            parent.$('#parentPermissionName').val(name);
            parent.layer.close(index);
        }
    </script>
</div>
</body>
</html>