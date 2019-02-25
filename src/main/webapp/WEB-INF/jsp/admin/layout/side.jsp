<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 侧边栏 -->
<aside class="main-sidebar" style="position: fixed">
  <section class="sidebar">
    <ul class="sidebar-menu">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/img/user2-160x160.jpg" class="img-circle"
               alt="User Image">
        </div>
        <div class="pull-left info">
        <p>public</p>
          <a href="#"><i class="fa fa-circle text-success"></i>超级管理员</a>
        </div>
      </div>
      <li class="header">MAIN NAVIGATION</li>
      
        <li>
          <a href="/admin/index">
            <i class="fa fa-dashboard"></i>
            <span>仪表盘</span>
          </a>
        </li>
      
      
        <li>
          <a href="/admin/topic/list">
            <i class="fa fa-list"></i>
            <span>话题</span>
          </a>
        </li>
      
      
        <li>
          <a href="/admin/comment/list">
            <i class="fa fa-comment"></i>
            <span>评论</span>
          </a>
        </li>
      
      
        <li>
          <a href="/admin/tag/list">
            <i class="fa fa-tags"></i>
            <span>节点</span>
          </a>
        </li>
      
      
        <li>
          <a href="/admin/user/list">
            <i class="fa fa-user"></i>
            <span>用户</span>
          </a>
        </li>
      
      
        <li class="treeview">
          <a href="#">
            <i class="fa fa-server"></i> <span>权限管理</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            
              <li>
                <a href="/admin/admin_user/list">
                  <i class="fa fa-circle-o"></i>
                  2
                </a>
              </li>
            
           
            <li>
              <a href="/admin/role/list">
                <i class="fa fa-circle-o"></i>
                2
              </a>
            </li>
            
            
            <li>
              <a href="/admin/permission/list">
                <i class="fa fa-circle-o"></i>
                2
              </a>
            </li>
            
          </ul>
        </li>
      
      
        <li>
          <a href="/admin/system/edit">
            <i class="fa fa-cogs"></i>
            <span>2</span>
          </a>
        </li>
      
      <li>
        <a href="/admin/logout">
          <i class="fa fa-sign-out"></i>
          <span>2</span>
        </a>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>