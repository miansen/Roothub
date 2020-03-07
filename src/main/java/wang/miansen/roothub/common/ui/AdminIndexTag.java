package wang.miansen.roothub.common.ui;

import javax.servlet.http.HttpServletRequest;

/**
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
		sb.append("<link rel=\"icon\" href=\""+getContextPath()+"/admin/resource/images/favicon.ico\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\""+getContextPath()+"/admin/resource/layui/css/layui.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css\" />");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"/>");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css\"/>");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/AdminLTE.min.css\" />");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.8/css/skins/_all-skins.min.css\" />");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css\" />");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\""+getContextPath()+"/admin/resource/css/admin.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\""+getContextPath()+"/admin/resource/wangEditor/wangEditor.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\""+getContextPath()+"/admin/resource/cropper/cropper.css\">");
		sb.append("\t\n");
		sb.append("<style>");
		sb.append("\t\n");
		sb.append("@media screen and (max-width: 768px) {.content-header {position: relative;padding: 65px 15px 0 15px;}}");
		sb.append("\t\n");
		sb.append("</style>");
		sb.append("\t\n");
		sb.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\""+getContextPath()+"/admin/resource/js/app.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\""+getContextPath()+"/admin/resource/layui/layui.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\""+getContextPath()+"/admin/resource/js/avatar.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\""+getContextPath()+"/admin/resource/cropper/cropper.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script>");
		sb.append("\t\n");
		sb.append("function toast(txt, icon) {$.toast({text: txt, heading: '系统提醒', icon: icon || 'error', showHideTransition: 'slide', allowToastClose: true, hideAfter: 2000, stack: false, position: 'top-right'});}");
		sb.append("\t\n");
		sb.append(" </script>");
		sb.append("\t\n");
		sb.append("</head>");
		sb.append("\t\n");
		sb.append("<body class=\"hold-transition skin-blue sidebar-mini\" style=\"background-color: #ecf0f5;\">");
		sb.append("\t\n");
		sb.append("<header class=\"main-header\" style=\"position: fixed; width: 100%\">");
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
		sb.append("<div class=\"content-wrapper\" style=\"padding: 50px 0 40px;height: 100%;\">");
		sb.append("\t\n");
		sb.append("<iframe name=\"roothub-iframe\" src=\""+ getContextPath() + "/admin/console" +"\" width=\"100%\" height=\"100%\" frameborder=\"no\" border=\"0\">");
		sb.append("\t\n");
		sb.append("</iframe>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<footer class=\"main-footer\" style=\"position: fixed; bottom: 0; width: 100%;\">");
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
		sb.append("</body>");
		sb.append("\t\n");
		sb.append("</html>");
		return sb.toString();
	}
	

}
