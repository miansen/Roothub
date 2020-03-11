package wang.miansen.roothub.common.ui;

/**
 * 头部导航标签
 * 
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class MainHeaderTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<header class=\"main-header\">");
		sb.append("\t\n");
		
		// Logo 开始
		sb.append("<a href=\""+getContextPath()+"/admin/index\" class=\"logo\">");
		sb.append("\t\n");
		sb.append("<span class=\"logo-mini\"><b>Roothub</b></span>");
		sb.append("\t\n");
		sb.append("<span class=\"logo-lg\"><b>Roothub</b>后台管理</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		// Logo 结束
		
		/*Header Navbar start*/
		sb.append("<nav class=\"navbar navbar-static-top\" role=\"navigation\">");
		sb.append("\t\n");
		// Sidebar toggle button
		sb.append("<a href=\""+getContextPath()+"/admin/index\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">");
		sb.append("\t\n");
		sb.append("<span class=\"sr-only\">Toggle navigation</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		// Navbar Right Menu
		sb.append("<div class=\"navbar-custom-menu\">");
		sb.append("\t\n");
		sb.append("<ul class=\"nav navbar-nav\">");
		sb.append("\t\n");
		// 邮件通知栏开始
		sb.append("<li class=\"dropdown messages-menu\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">");
		sb.append("\t\n");
		sb.append("<i class=\"fa fa-envelope-o\"></i>");
		sb.append("\t\n");
		sb.append("<span class=\"label label-success\">4</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("\t\n");
		sb.append("<li class=\"header\">You have 4 messages</li>");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<ul class=\"menu\">");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"#\">");
		sb.append("\t\n");
		sb.append("<div class=\"pull-left\">");
		sb.append("\t\n");
		sb.append("<img src=\"dist/img/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<h4>");
		sb.append("\t\n");
		sb.append("Support Team");
		sb.append("\t\n");
		sb.append("<small><i class=\"fa fa-clock-o\"></i> 5 mins</small>");
		sb.append("\t\n");
		sb.append("</h4>");
		sb.append("\t\n");
		sb.append("<p>Why not buy a new awesome theme?</p>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li class=\"footer\"><a href=\"#\">See All Messages</a></li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		// 邮件通知栏结束
		
		// 消息通知栏开始
		sb.append("<li class=\"dropdown notifications-menu\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">");
		sb.append("\t\n");
		sb.append("<i class=\"fa fa-bell-o\"></i>");
		sb.append("\t\n");
		sb.append("<span class=\"label label-warning\">10</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("\t\n");
		sb.append("<li class=\"header\">You have 10 notifications</li>");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<ul class=\"menu\">");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"#\">");
		sb.append("\t\n");
		sb.append("<i class=\"fa fa-users text-aqua\"></i> 5 new members joined today");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li class=\"footer\"><a href=\"#\">View all</a></li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		// 消息通知栏结束
		
		// 任务进度栏开始
		sb.append("<li class=\"dropdown tasks-menu\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">");
		sb.append("\t\n");
		sb.append("<i class=\"fa fa-flag-o\"></i>");
		sb.append("\t\n");
		sb.append("<span class=\"label label-danger\">9</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("\t\n");
		sb.append("<li class=\"header\">You have 9 tasks</li>");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<ul class=\"menu\">");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"#\">");
		sb.append("\t\n");
		sb.append("<h3>");
		sb.append("\t\n");
		sb.append("Design some buttons");
		sb.append("\t\n");
		sb.append("<small class=\"pull-right\">20%</small>");
		sb.append("\t\n");
		sb.append("</h3>");
		sb.append("\t\n");
		sb.append("<div class=\"progress xs\">");
		sb.append("\t\n");
		sb.append("<div class=\"progress-bar progress-bar-aqua\" style=\"width: 20%\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\">");
		sb.append("\t\n");
		sb.append("<span class=\"sr-only\">20% Complete</span>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</li>");
		// 任务进度栏结束
		
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li class=\"footer\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\">View all tasks</a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		
		// 用户栏开始
		sb.append("<li class=\"dropdown user user-menu\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">");
		sb.append("\t\n");
		sb.append("<img src=\"dist/img/user2-160x160.jpg\" class=\"user-image\" alt=\"User Image\">");
		sb.append("\t\n");
		sb.append("<span class=\"hidden-xs\">Alexander Pierce</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("\t\n");
		sb.append("<li class=\"user-header\">");
		sb.append("\t\n");
		sb.append("<img src=\"dist/img/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">");
		sb.append("\t\n");
		sb.append("<p>Alexander Pierce - Web Developer<small>Member since Nov. 2012</small></p>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li class=\"user-body\">");
		sb.append("\t\n");
		sb.append("<div class=\"row\">");
		sb.append("\t\n");
		sb.append("<div class=\"col-xs-4 text-center\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\">Followers</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<div class=\"col-xs-4 text-center\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\">Sales</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<div class=\"col-xs-4 text-center\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\">Friends</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li class=\"user-footer\">");
		sb.append("\t\n");
		sb.append("<div class=\"pull-left\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"btn btn-default btn-flat\">Profile</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<div class=\"pull-right\">");
		sb.append("\t\n");
		sb.append("<a href=\"#\" class=\"btn btn-default btn-flat\">Sign out</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"#\" data-toggle=\"control-sidebar\"><i class=\"fa fa-gears\"></i></a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</nav>");
		sb.append("\t\n");
		sb.append("</header>");
		return sb.toString();
	}
	

}
