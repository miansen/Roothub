package wang.miansen.roothub.common.ui;

/**
 * 控制侧边栏标签
 * 
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class ControlSidebarTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<aside class=\"control-sidebar control-sidebar-dark\">");
		sb.append("\t\n");
		sb.append("<ul class=\"nav nav-tabs nav-justified control-sidebar-tabs\">");
		sb.append("\t\n");
		sb.append("<li class=\"active\"><a href=\"#control-sidebar-home-tab\" data-toggle=\"tab\"><i class=\"fa fa-home\"></i></a></li>");
		sb.append("\t\n");
		sb.append("<li><a href=\"#control-sidebar-settings-tab\" data-toggle=\"tab\"><i class=\"fa fa-gears\"></i></a></li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("<div class=\"tab-content\">");
		sb.append("\t\n");
		sb.append("<div class=\"tab-pane active\" id=\"control-sidebar-home-tab\">");
		sb.append("\t\n");
		sb.append("<h3 class=\"control-sidebar-heading\">Recent Activity</h3>");
		sb.append("\t\n");
		sb.append("<ul class=\"control-sidebar-menu\">");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"javascript:;\">");
		sb.append("\t\n");
		sb.append("<i class=\"menu-icon fa fa-birthday-cake bg-red\"></i>");
		sb.append("\t\n");
		sb.append("<div class=\"menu-info\">");
		sb.append("\t\n");
		sb.append("<h4 class=\"control-sidebar-subheading\">Langdon's Birthday</h4>");
		sb.append("\t\n");
		sb.append("<p>Will be 23 on April 24th</p>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("<h3 class=\"control-sidebar-heading\">Tasks Progress</h3>");
		sb.append("\t\n");
		sb.append("<ul class=\"control-sidebar-menu\">");
		sb.append("\t\n");
		sb.append("<li>");
		sb.append("\t\n");
		sb.append("<a href=\"javascript:;\">");
		sb.append("\t\n");
		sb.append("<h4 class=\"control-sidebar-subheading\">");
		sb.append("\t\n");
		sb.append("Custom Template Design");
		sb.append("\t\n");
		sb.append("<span class=\"pull-right-container\">");
		sb.append("\t\n");
		sb.append("<span class=\"label label-danger pull-right\">70%</span>");
		sb.append("\t\n");
		sb.append("</span>");
		sb.append("\t\n");
		sb.append("</h4>");
		sb.append("\t\n");
		sb.append("<div class=\"progress progress-xxs\">");
		sb.append("\t\n");
		sb.append("<div class=\"progress-bar progress-bar-danger\" style=\"width: 70%\"></div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</li>");
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<div class=\"tab-pane\" id=\"control-sidebar-stats-tab\">Stats Tab Content</div>");
		sb.append("\t\n");
		sb.append("<div class=\"tab-pane\" id=\"control-sidebar-settings-tab\">");
		sb.append("\t\n");
		sb.append("<form method=\"post\">");
		sb.append("\t\n");
		sb.append("<h3 class=\"control-sidebar-heading\">General Settings</h3>");
		sb.append("\t\n");
		sb.append("<div class=\"form-group\">");
		sb.append("\t\n");
		sb.append("<label class=\"control-sidebar-subheading\">");
		sb.append("\t\n");
		sb.append("Report panel usage");
		sb.append("\t\n");
		sb.append("<input type=\"checkbox\" class=\"pull-right\" checked>");
		sb.append("\t\n");
		sb.append("</label>");
		sb.append("\t\n");
		sb.append("<p>Some information about this general settings option</p>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</form>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</aside>");
		return sb.toString();
	}
	

}
