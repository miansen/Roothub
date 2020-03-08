package wang.miansen.roothub.common.ui;

/**
 * @author miansen.wang
 * @date 2020-03-01
 */
@SuppressWarnings("serial")
public class AdminLayoutTag extends AbstractBaseTag {

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
		sb.append("<script src=\""+getContextPath()+"/admin/resource/js/openIframeDialog.js\"></script>");
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
		sb.append(bodyContent);
		sb.append("\t\n");
		sb.append("</body>");
		sb.append("\t\n");
		sb.append("</html>");
		return sb.toString();
	}
	

}
