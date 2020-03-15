package wang.miansen.roothub.modules.sidebar.ui;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import wang.miansen.roothub.common.ui.AbstractListTag;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.common.ui.util.HtmlElementUtils;
import wang.miansen.roothub.modules.sidebar.vo.SidebarVO;

/**
 * 侧边栏 List 标签
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
@SuppressWarnings("serial")
public class SidebarListTag extends AbstractListTag {

	@Override
	protected TableTagThSupport getTableTagThSupport() {
		return () -> "操作";
	}

	@Override
	protected TableTagTdSupport getTableTagTdSupport() {
		String contextPath = super.getContextPath();
		HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
		String dialog = request.getParameter("dialog");
		return object -> {
			StringBuilder sb = new StringBuilder();
			SidebarVO sidebarVO = (SidebarVO) object;
			if (Objects.equals(dialog, "true")) {
				sb.append("<a href=\"javascript:void(0);\" onclick=\"selectSidebar(\""+sidebarVO.getSidebarId()+"\",\""+sidebarVO.getSidebarName()+"\")\" class=\"btn btn-xs btn-primary\">选择</a>");
				sb.append("\t\n");
				sb.append(HtmlElementUtils.convertScript("function selectSidebar(){parent.$('#parentSidebarId').val(id);parent.$('#parentSidebarName').val(name);parent.bootbox.hideAll();}"));
			} else {
				String editPath = contextPath + "/admin/sidebar/edit?id=" + sidebarVO.getSidebarId();
				String removePath = contextPath + "/admin/sidebar/remove?id="+ sidebarVO.getSidebarId();
				sb.append("<a href=\"javascript:void(0);\" onclick=\"dialog.href('"+ editPath +"', 'roothub-iframe')\" class=\"btn btn-xs btn-warning\">编辑</a>");
				sb.append("\t\n");
				sb.append("<a href=\"javascript:void(0);\" onclick=\"dialog.deleteConfirm('"+ removePath +"')\" class=\"btn btn-xs btn-danger\">删除</a>");
			}
			return sb.toString();
		};
	}

}
