package wang.miansen.roothub.common.ui;

/**
 * 后台首页标签
 * 
 * @author miansen.wang
 * @date 2020-03-01
 */
@SuppressWarnings("serial")
public class AdminIndexTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!doctype html>");
		sb.append("\t\n");
		sb.append("<html lang=\"zh-CN\">");
		sb.append("\t\n");
		sb.append("<head>");
		sb.append("\t\n");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("\t\n");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">");
		sb.append("\t\n");
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		sb.append("\t\n");
		sb.append("<title>Roothub后台管理系统</title>");
		sb.append("\t\n");
		sb.append("<link rel=\"icon\" href=\""+ getContextPath() +"/admin/resource/images/favicon.ico\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/bootstrap-v3.4.1/dist/css/bootstrap.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/font-awesome-v4.7.0/css/font-awesome.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/Ionicons-v2.0.0/css/ionicons.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/AdminLTE-v2.4.18/dist/css/AdminLTE.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/AdminLTE-v2.4.18/dist/css/skins/_all-skins.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/bootstrap-datepicker-v1.9.0/dist/css/bootstrap-datepicker.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/cropper-v3.1.3/cropper.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"" + getContextPath() + "/libs/wangEditor/wangEditor.min.css\">");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/jquery-v3.4.1/dist/jquery.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/bootstrap-v3.4.1/dist/js/bootstrap.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/AdminLTE-v2.4.18/dist/js/adminlte.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/bootstrap-datepicker-v1.9.0/dist/js/bootstrap-datepicker.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/bootstrap-paginator-1.0.2/bootstrap-paginator.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/bootbox-v5.3.2/bootbox.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/cropper-v3.1.3/cropper.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/libs/wangEditor/wangEditor.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"" + getContextPath() + "/admin/resource/js/avatar.js\"></script>");
		sb.append("\t\n");
		sb.append("</head>");
		sb.append("\t\n");
		sb.append("<body class=\"hold-transition skin-blue sidebar-mini\">");
		sb.append("\t\n");
		sb.append("<div class=\"wrapper\">");
		sb.append("\t\n");
		sb.append("<header class=\"main-header\">");
		sb.append("\t\n");
		sb.append("<a href=\""+getContextPath()+"/admin/index\" class=\"logo\">");
		sb.append("\t\n");
		sb.append("<span class=\"logo-mini\"><b>Roothub</b></span>");
		sb.append("\t\n");
		sb.append("<span class=\"logo-lg\"><b>Roothub</b>后台管理</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("<nav class=\"navbar navbar-static-top\">");
		sb.append("\t\n");
		sb.append("<a href=\""+getContextPath()+"/admin/index\" class=\"sidebar-toggle\" data-toggle=\"offcanvas\" role=\"button\">");
		sb.append("\t\n");
		sb.append("<span class=\"sr-only\">Toggle navigation</span>");
		sb.append("\t\n");
		sb.append("</a>");
		sb.append("\t\n");
		sb.append("</nav>");
		sb.append("\t\n");
		sb.append("</header>");
		sb.append("\t\n");
		sb.append(bodyContent);
		sb.append("\t\n");
		sb.append("<div class=\"content-wrapper\">");
		sb.append("\t\n");
		sb.append("<iframe name=\"roothub-iframe\" src=\""+ getContextPath() + "/admin/console" +"\" width=\"100%\" height=\"100%\" frameborder=\"no\" border=\"0\">");
		sb.append("\t\n");
		sb.append("</iframe>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<footer class=\"main-footer\">");
		sb.append("\t\n");
		sb.append("<div class=\"pull-right hidden-xs\">");
		sb.append("\t\n");
		sb.append("<b>Version</b> 2.4.8");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<strong>Copyright © 2018-2020 <a href=\"https://github.com/miansen/Roothub\">Roothub</a>.</strong> All rights reserved.");
		sb.append("\t\n");
		sb.append("</footer>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</body>");
		sb.append("\t\n");
		sb.append("</html>");
		return sb.toString();
	}
	

}
