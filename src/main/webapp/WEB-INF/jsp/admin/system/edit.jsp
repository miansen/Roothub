<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
      系统
      <small>设置</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/system/edit">系统</a></li>
      <li class="active">设置</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">${systemConfig.description}</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form" action="/admin/role/edit" method="post">
          <%-- <input type="hidden" name="roleId" id="roleId" value="${role.roleId}"> --%>
          <div class="form-group">
            <c:forEach items="${systemConfigs}" var="systemConfig" >
			<c:if test="${systemConfig.type == 'text'}">
			 	<label>${systemConfig.description}</label>
               	<input type="text" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
			<c:if test="${systemConfig.type == 'radio'}">
				<c:if test="${systemConfig.value == '1'}">
					${systemConfig.description}<input type="radio" name="radios" id="${systemConfig.key}" value="${systemConfig.systemConfigId}" checked="checked"/>
				</c:if>
               	<c:if test="${systemConfig.value == '0'}">
					${systemConfig.description}<input type="radio" name="radios" id="${systemConfig.key}" value="${systemConfig.systemConfigId}"/>
				</c:if>
            </c:if>
			</c:forEach>
          </div>
          <button type="submit" class="btn btn-primary">保存</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  $(function() {
	  $(".system-menu").addClass("active");
	  $(".system-treeview-menu li:eq(${pid})").addClass("active");

	  // 获取已点击的单选框
	  var uploadValue = $("input[name='radios']:checked").attr("value")
	  //console.log(uploadValue);
	  // 获取上传配置
	  if (uploadValue) {
	    $.ajax({
	      url: '/admin/system/list',
	      async: true,
	      cache: false,
	      type: 'get',
	      dataType: 'json',
	      data: {
	        pid: uploadValue
	      },
	      success: function(data) {
	        if (data.success === true) {
	          //console.log(data.data);
	          $(".form-group").append('<div class="upload-input" style="margin-top: 10px;"></>');
	          for (var i = 0; i < data.data.length; i++) {
	            $(".upload-input").append('<label>' + data.data[i].description + '</label><input type="text" name="' + data.data[i].key + '" id="' + data.data[i].key + '" value="' + data.data[i].value + '" class="form-control"/><br/>');
	          }
	        } else {
	          toast(data.error, 'error');
	        }
	      }
	    })
	  }

	  // 切换上传配置
	  $("input[name=radios]").each(function() {
	    $(this).click(function() {
	      var pid = $(this).val();
	      if (pid) {
	        $.ajax({
	          url: '/admin/system/list',
	          async: true,
	          cache: false,
	          type: 'get',
	          dataType: 'json',
	          data: {
	            pid: pid
	          },
	          success: function(data) {
	            if (data.success === true) {
	              $(".upload-input").html('');
	              for (var i = 0; i < data.data.length; i++) {
	                $(".upload-input").append('<label>' + data.data[i].description + '</label><input type="text" name="' + data.data[i].key + '" id="' + data.data[i].key + '" value="' + data.data[i].value + '" class="form-control"/><br/>');
	              }
	            } else {
	              toast(data.error, 'error');
	            }
	          }
	        })
	      }

	    });
	  });

	  $("#form").submit(function() {
	    var roleName = $("#roleName").val();
	    var permissionIds = [];
	    $("input[name='permissionIds']:checked").each(function(index, item) {
	      permissionIds.push($(this).val());
	    });
	    if (!roleName) {
	      toast('角色名不能为空');
	      return false;
	    }
	    $.ajax({
	      url: '/admin/role/edit',
	      async: true,
	      cache: false,
	      type: 'post',
	      dataType: 'json',
	      traditional: true,
	      //防止深度序列化,默认false
	      data: {
	        roleId: roleId,
	        roleName: roleName,
	        permissionIds: permissionIds
	      },
	      success: function(data) {
	        if (data.success === true) {
	          toast('编辑成功', 'success');
	          setTimeout(function() {
	            window.location.reload();
	          },
	          1000);
	        } else {
	          toast(data.error, 'error');
	        }
	      }
	    })
	  })
	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>