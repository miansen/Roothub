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
        <form id="form">
          <%-- <input type="hidden" name="pid" id="pid" value="${systemConfig.systemConfigId}"> --%>
          <div class="form-group">
            <c:forEach items="${systemConfigs}" var="systemConfig" >
            
			<c:if test="${systemConfig.type == 'text'}">
			 	<label>${systemConfig.description}</label>
               	<input type="text" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
            <c:if test="${systemConfig.type == 'number'}">
			 	<label>${systemConfig.description}</label>
               	<input type="number" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
            <c:if test="${systemConfig.type == 'hidden'}">
               	<input type="hidden" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
           <!--  普通的radio -->
            <c:if test="${systemConfig.type == 'radio' && systemConfig.pid != 2}">
            	<label>${systemConfig.description}</label><br/>
            	<c:if test="${systemConfig.value == '1'}">
					<span style="padding-right: 5px;">是</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="1" checked="checked"/>
					<span style="padding-right: 5px;">否</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="0"/>
				</c:if>
               	<c:if test="${systemConfig.value == '0'}">
               		<span style="padding-right: 5px;">是</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="1"/>
					<span style="padding-right: 5px;">否</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="0" checked="checked"/>
				</c:if>
            </c:if>
            <!--  普通的radio -->
            
            <!-- 上传设置的radio -->
			<c:if test="${systemConfig.type == 'radio' && systemConfig.pid == 2}">
				<c:if test="${systemConfig.value == '1'}">
					<span style="font-weight: 700;padding-right: 5px;">${systemConfig.description}</span><input type="radio" name="upload-type" id="${systemConfig.key}" value="${systemConfig.systemConfigId}" checked="checked"/>
				</c:if>
               	<c:if test="${systemConfig.value == '0'}">
					<span style="font-weight: 700;padding-right: 5px;">${systemConfig.description}</span><input type="radio" name="upload-type" id="${systemConfig.key}" value="${systemConfig.systemConfigId}"/>
				</c:if>
            </c:if>
            <!-- 上传设置的radio -->
            
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
	  $(".system-treeview-menu li:eq(${index})").addClass("active");

	  // 获取已点击的单选框
	  var uploadValue = $("input[name='upload-type']:checked").attr("value")
	  //console.log(uploadValue);
	  // 获取上传配置
	  if (uploadValue) {
	    $.ajax({
	      url: '/admin/system/upload/list',
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
	          $(".form-group").append('<div class="upload-input" style="margin-top: 20px;"></>');
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
	  $("input[name=upload-type]").each(function() {
	    $(this).click(function() {
	      var id = $(this).val();
	      if (id) {
	    	$("#upload_type").val(id);
	        $.ajax({
	          url: '/admin/system/upload/list',
	          async: true,
	          cache: false,
	          type: 'get',
	          dataType: 'json',
	          data: {
	            pid: id
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

	  // 更新配置
	  $("#form").submit(function() {
	    $.ajax({
	      url: '/admin/system/edit',
	      contentType:"application/json; charset=utf-8",
	      type: 'post',
	      dataType: 'json',
	      data: JSON.stringify($("#form").serializeArray()),
	      success: function(data) {
	        if (data.success === true) {
	          toast('编辑成功', 'success');
	          setTimeout(function() {
	        	  window.location.reload();
	          },1000);
	          console.log(data);
	        } else {
	          toast(data.error, 'error');
	        }
	      }
	    })
	    return false;
	  })
	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>