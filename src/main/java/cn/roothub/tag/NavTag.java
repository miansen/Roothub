package cn.roothub.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.util.StringUtils;

import cn.roothub.entity.Node;
import cn.roothub.service.NodeService;
import cn.roothub.util.ApplicationContextUtil;

/**
 * 导航标签
 * 
 * @author miansen.wang
 * @date 2020-04-15
 */
public class NavTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) super.getJspContext();
		ServletRequest request = pageContext.getRequest();
		Object nodeName = request.getAttribute("nodeName");
		NodeService nodeService = ApplicationContextUtil.getBean(NodeService.class);
		List<Node> nodes = nodeService.listForNav();
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"tab-nav\">");
		sb.append("\t\n");
		sb.append("<div class=\"container\" style=\"height: 45px;\">");
		sb.append("\t\n");
		sb.append("<ul style=\"padding-left: 0px\">");
		sb.append("\t\n");
		if ("index".equals(nodeName)) {
			sb.append("<li class=\"li-cate\"><a href=\"/\" class=\"li-cate-active\">首页</a></li>");
		} else {
			sb.append("<li class=\"li-cate\"><a href=\"/\" class=\"li-cate-a\">首页</a></li>");
		}
		sb.append("\t\n");
		for (Node node : nodes) {
			if (node.getNodeTitle().equals(nodeName)) {
				sb.append("<li class=\"li-cate\"><a href=\"/?node="+ node.getNodeTitle() +"\" class=\"li-cate-active\">"+ node.getNodeTitle() +"</a></li>");
			} else {
				sb.append("<li class=\"li-cate\"><a href=\"/?node="+ node.getNodeTitle() +"\" class=\"li-cate-a\">"+ node.getNodeTitle() +"</a></li>");
			}
			sb.append("\t\n");
		}
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		super.getJspContext().getOut().append(sb.toString());
	}
	
}
