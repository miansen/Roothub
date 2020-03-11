package wang.miansen.roothub.common.ui;

/**
 * 页脚标签
 * 
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class MainFooterTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<footer class=\"main-footer\">");
		sb.append("\t\n");
		sb.append(bodyContent);
		sb.append("\t\n");
		sb.append("<div class=\"pull-right hidden-xs\">");
		sb.append("\t\n");
		sb.append("Anything you want");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<strong>Copyright &copy; 2016 <a href=\"#\">Company</a>.</strong> All rights reserved.");
		sb.append("\t\n");
		sb.append("</footer>");
		return sb.toString();
	}
	

}
