<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- 侧边栏 -->
<aside class="main-sidebar" style="position: fixed">
  <section class="sidebar">
    <ul class="sidebar-menu">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/resources/images/default-avatar.jpg" class="img-circle"
               alt="User Image" id="user-avatar">
        </div>
        <div class="pull-left info">
        <p id="user-name">admin</p>
          <a href="#"><i class="fa fa-circle text-success"></i>管理员</a>
        </div>
      </div>
      <li class="header">MAIN NAVIGATION</li>
      <shiro:hasPermission name="index:index">
        <li>
          <a href="/admin/index">
            <i class="fa fa-dashboard"></i>
            <span>仪表盘</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="topic:list">
        <li>
          <a href="/admin/topic/list">
            <i class="fa fa-list"></i>
            <span>话题</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="reply:list">
        <li>
          <a href="/admin/reply/list">
            <i class="fa fa-comment"></i>
            <span>评论</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="node:list">
        <li>
          <a href="/admin/node/list">
            <i class="fa fa-tags"></i>
            <span>节点</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="user:list">
        <li>
          <a href="/admin/user/list">
            <i class="fa fa-user"></i>
            <span>用户</span>
          </a>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="permission:list">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-server"></i> <span>权限管理</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <shiro:hasPermission name="admin_user:list">
              <li>
                <a href="/admin/admin_user/list">
                  <i class="fa fa-circle-o"></i>后台用户列表
                </a>
              </li>
            </shiro:hasPermission>
           <shiro:hasPermission name="role:list">
            <li>
              <a href="/admin/role/list">
                <i class="fa fa-circle-o"></i>  角色列表
              </a>
            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="permission:list">
            <li>
              <a href="/admin/permission/list">
                <i class="fa fa-circle-o"></i>权限列表
              </a>
            </li>
            </shiro:hasPermission>
          </ul>
        </li>
      </shiro:hasPermission>
      <shiro:hasPermission name="system:edit">
        <li class="system-menu">
          <a href="#">
            <i class="fa fa-cogs"></i>
            <span>系统设置</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu system-treeview-menu">
            <shiro:hasPermission name="system:edit">
              <li>
                <a href="/admin/system/edit">
                  <i class="fa fa-circle-o"></i>基础配置
                </a>
              </li>
            </shiro:hasPermission>
            
            <shiro:hasPermission name="system:edit">
            <li>
              <a href="/admin/system/edit?pid=48&index=1">
                <i class="fa fa-circle-o"></i>分页设置
              </a>
            </li>
            </shiro:hasPermission>
            
            <shiro:hasPermission name="system:edit">
            <li>
              <a href="/admin/system/edit?pid=4&index=2">
                <i class="fa fa-circle-o"></i>积分设置
              </a>
            </li>
            </shiro:hasPermission>
            
           <shiro:hasPermission name="system:edit">
            <li>
              <a href="/admin/system/edit?pid=2&index=3">
                <i class="fa fa-circle-o"></i>上传设置
              </a>
            </li>
            </shiro:hasPermission>
            
            <shiro:hasPermission name="system:edit">
            <li>
              <a href="/admin/system/edit?pid=5&index=4">
                <i class="fa fa-circle-o"></i>Redis设置
              </a>
            </li>
            </shiro:hasPermission>
            
          </ul>
        </li>
      </shiro:hasPermission>
      <li>
        <a href="/admin/logout">
          <i class="fa fa-sign-out"></i>
          <span>登出</span>
        </a>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
  <script type="text/javascript">
  	$(function(){
  		$.ajax({
  			url: "/admin/user/info",
  			type: "get",
  			dataType: "json",
  			success: function(data){
  				if(data.success === true){
  					$("#user-name").text(data.data.username);
  					$("#user-avatar").attr("src",data.data.avatar);
  				}
  			},
  			error: function(data){
  				
  			}
  		});
  	})
  </script>
</aside>