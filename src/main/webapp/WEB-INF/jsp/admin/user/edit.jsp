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
      			<form id="form" action="/admin/tag/edit" method="post" enctype="multipart/form-data">
          			<input type="hidden" value="${user.userId}" name="userId" class="user-id">
          			<div class="form-group">
            			<label>用户名</label>
            			<input type="text" name="username" id ="username" value="${user.userName}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>密码</label>
            			<input type="password" name="password" id ="password" value="${user.password}" class="form-control">
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
            			<c:if test="${user.userSex == '男'}">
							<span style="padding-right: 5px;">男</span><input type="radio" name="userSex" id="userSex" value="男" checked="checked"/>
							<span style="padding-right: 5px;">女</span><input type="radio" name="userSex" id="userSex" value="女"/>
						</c:if>
            			<c:if test="${user.userSex == '女'}">
               				<span style="padding-right: 5px;">男</span><input type="radio" name="sex" id="sex" value="男"/>
							<span style="padding-right: 5px;">女</span><input type="radio" name="sex" id="sex" value="女" checked="checked"/>
						</c:if>
          			</div>
          			<div class="form-group">
            			<label>地址</label>
            			<input type="text" name="addr" id ="addr" value="${user.userAddr}" class="form-control">
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
            			<input type="text" name="github" id ="github" value="${user.thirdId}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Token</label>
            			<input type="text" name="token" id ="token" value="${user.thirdAccessToken}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>创建时间</label>
            			<input type="date" name="date" id ="date" value="<fmt:formatDate value='${user.createDate}' pattern='yyyy-MM-dd'/>" class="form-control">
          			</div>
          			<div class="form-group">
                		<label>头像</label>
                		<p>
          					<button type="button" class="btn btn-primary" id="choiceAvatarBtn">选择头像</button>
         					<button type="button" class="btn btn-success" id="confirmAvatarBtn">确认头像</button>
          					<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          					<input type="hidden" value="${user.avatar}" name="adminUserAvatar" id="adminUserAvatar">
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

  		$("#form").submit(function(){
  			// ID
  			var nodeId = $(".node-id").val();
  			// 名称
  			var nodeTitle = $(".node-name").val();
  			// 图标
  			var avatarNormal = $(".avatarNormal").val();
  			// 背景图
  			var avatarLarge = $(".avatarLarge").val();
  			// 描述
  			var nodeDesc = $(".node-desc").val();
  			var data = {nodeId, nodeTitle, avatarNormal, avatarLarge, nodeDesc};
  			if(confirm("确定要编辑此节点吗？")){
  				if(!nodeId){
  					toast('节点名称不能为空');
  					return false;
  				}
  				console.log(data);
  				$.ajax({
  					url: "/admin/node/edit",
  					type: "post",
  					dataType: "json",
  					data: data,
  					success: function(data){
  						if(data.success == true){
  							toast('编辑成功','success');
  	  						setTimeout(function(){
  	  							window.location.href = "/admin/node/list";
  	  						},1000);
  						}else{
  							toast(data.error,'error');
  						}
  					},
  					error: function(data){
  						
  					}
  				});
  			}
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>