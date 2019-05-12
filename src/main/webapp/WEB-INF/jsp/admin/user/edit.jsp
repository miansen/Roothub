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
     用户
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/user/list">用户</a></li>
      <li class="active">编辑</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">用户编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      	<div class="row">
      		<div class="col-sm-6">
      			<form id="form" onsubmit="return;">
          			<input type="hidden" value="${user.userId}" name="userId" id="userId" class="form-control">
          			<div class="form-group">
            			<label>用户名</label>
            			<input type="text" name="userName" id ="userName" value="${user.userName}" class="form-control" readonly="readonly">
          			</div>
          			<div class="form-group">
            			<label>密码</label>
            			<input type="password" name="password" id ="password" value="" class="form-control" placeholder="密码（为空不修改）">
        			</div>
          			<div class="form-group">
            			<label>邮箱</label>
            			<input type="email" name="email" id ="email" value="${user.email}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>积分</label>
            			<input type="number" name="score" id ="score" value="${user.score}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>性别</label>
                		<select name="userSex" id="userSex" class="form-control">                 
             			<c:choose>
                			<c:when test="${user.userSex == '男'}">
                				<option value="男" selected>男</option>
                				<option value="女">女</option>
                			</c:when>
                			<c:when test="${user.userSex == '女'}">
                				<option value="男">男</option>
                				<option value="女" selected>女</option>
                			</c:when>
                			<c:otherwise>
                				<option value="男">男</option>
                				<option value="女">女</option>
                			</c:otherwise>
                		</c:choose>
          				</select>
          			</div>
          			<div class="form-group">
            			<label>地址</label>
            			<input type="text" name="userAddr" id ="userAddr" value="${user.userAddr}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>个人主页</label>
            			<input type="text" name="url" id ="url" value="${user.url}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>个性签名</label>
            			<input type="text" name="signature" id ="signature" value="${user.signature}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Github</label>
            			<input type="text" name="thirdId" id ="thirdId" value="${user.thirdId}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Token</label>
            			<div class="input-group">
            				<input type="text" name="thirdAccessToken" id ="thirdAccessToken" value="${user.thirdAccessToken}" class="form-control">
            				<span class="input-group-btn">
                  				<button type="button" onclick="refreshToken(this)" class="btn btn-info" autocomplete="off"
                          				data-loading-text="刷新中...">刷新Token</button>
                  				<script>
                    				function refreshToken(self) {
                    					// 当加载时，按钮是禁用的，且文本变为 button 元素的 data-loading-text 属性的值
                      					$(self).button("loading");
                      					$.get("/admin/user/refreshToken", function(data) {
                        				if (data.success === true) {
                          					toast("刷新成功", "success");
                         	 				$("#thirdAccessToken").val(data.error);
                        				} else {
                          					toast(data.error);
                        					}
                        				// 重置按钮状态，文本内容恢复为最初的内容
                        				$(self).button("reset");
                      					});
                    				}
                  				</script>
                			</span>
               			</div>
          			</div>
          			<div class="form-group">
            			<label>创建时间</label>
            			<input type="date" name="createDate" id ="createDate" value="<fmt:formatDate value='${user.createDate}' pattern='yyyy-MM-dd'/>" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>有新消息接收邮件通知</label>
                		<select name="receiveMsg" id="receiveMsg" class="form-control">                 
             			<c:choose>
                			<c:when test="${user.receiveMsg == true}">
                				<option value="true" selected>是</option>
                				<option value="false">否</option>
                			</c:when>
                			<c:when test="${user.receiveMsg == false}">
                				<option value="true">是</option>
                				<option value="false" selected>否</option>
                			</c:when>
                			<c:otherwise>
                				<option value="true">是</option>
                				<option value="false">否</option>
                			</c:otherwise>
                		</c:choose>
          				</select>
          			</div>
          			<div class="form-group">
                		<label>头像</label>
                		<p>
          					<button type="button" class="btn btn-primary" id="choiceAvatarBtn">选择头像</button>
         					<button type="button" class="btn btn-success" id="confirmAvatarBtn">确认头像</button>
          					<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          					<input type="hidden" value="${user.avatar}" name="avatar" id="adminUserAvatar">
        				</p>
        				<div class="row">
          					<div class="col-md-9" id="adjustment">
            					<img src="${user.avatar}" id="newAvatarImg" class="origin-avatar" alt="">
          					</div>
          					<div class="col-md-3">
            				<div class="preview"></div>
          					</div>
        				</div>
              		</div>
          			<button type="submit" id="btn" class="btn btn-primary">提交</button>
        		</form>
      		</div>
      	</div>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(5)").addClass("active");

  		$("#btn").click(function(){
  			if(confirm("确定要编辑吗？")){
  				console.log($("#form").serialize());
  				$.post("/admin/user/edit",$("#form").serialize(),function(data){
  					if(data.success === true){
  						toast("编辑成功", "success");
  						setTimeout(function(){
  							window.location.href = "/admin/user/list";
  						},700);
  					}else{
  						toast(data.error);
  					}
  				});
  			}
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>