<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Roothub后台管理系统</title>
    <link rel="icon" href="/default/front/common/images/favicon.ico">
    <link rel="stylesheet" href="/default/front/common/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/skins/_all-skins.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <link rel="stylesheet" href="/default/admin/common/css/admin.css">
    <link rel="stylesheet" href="/default/front/common/wangEditor/wangEditor.min.css">
    <link rel="stylesheet" href="/default/front/common/cropper/cropper.css">
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
    <script src="/default/admin/common/js/app.min.js"></script>
    <script src="/default/front/common/layui/layui.js"></script>
    <script src="/default/front/common/layui/layui-paginate.js"></script>
    <script src="/default/admin/common/js/avatar.js"></script>
    <script src="/default/front/common/cropper/cropper.min.js"></script>

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
<div>
<!-- 内容主体区域 -->
<div class="content-wrapper">
    <section class="content">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">父节点列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <form action="/admin/node/parent" method="get" class="form-inline">
                    <div class="form-group" style="margin-bottom: 10px;">
                        <input type="text" name="nodeTitle" value="${nodeTitle}" class="form-control" placeholder="节点名" style="display: inline-block;width: 50%;">
                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
                    </div>
                </form>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>名称</th>
                        <th>选择</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="node" items="${page.list}">
                        <tr>
                            <td>${node.nodeId}</td>
                            <td>${node.nodeTitle}</td>
                            <td><a href="javascript:void(0);" onclick="toParent('${node.nodeTitle}')">选择</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="panel-footer" id="paginate"></div>
        </div>
    </section>
    <script type="text/javascript">

        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        /**
         * 选择父节点
         * 将选择的值赋给父页面，同时关闭子弹窗。
         * 需要注意：父页面取值用的选择器必须和赋值给父页面的选择器一样，
         * 比如这里是用 ID 选择器，那么取值也必须用 ID 选择器！
         * @param nodeTitle：父节点名称
         */
        function toParent(nodeTitle) {
            parent.$('#parentNodeCode').val(nodeTitle);
            parent.layer.close(index);
        }

        // 分页
        paginate(${page.totalRow},${page.pageSize},${p},"?p=");
    </script>
</div>
</div>
</body>
</html>