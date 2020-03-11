package wang.miansen.roothub.common.ui;

/**
 * 内容标签
 * 
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class ContentWrapperTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"content-wrapper\">");
		sb.append("\t\n");
		sb.append(bodyContent);
		sb.append("\t\n");
		sb.append("</div>");
		return sb.toString();
	}
	

}
