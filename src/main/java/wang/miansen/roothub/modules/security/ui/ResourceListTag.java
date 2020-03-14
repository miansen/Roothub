package wang.miansen.roothub.modules.security.ui;

import wang.miansen.roothub.common.ui.AbstractListTag;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.common.ui.util.HtmlElementUtils;
import wang.miansen.roothub.modules.security.vo.ResourceVO;

/**
 * 资源 List 标签
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
@SuppressWarnings("serial")
public class ResourceListTag extends AbstractListTag {

	@Override
	protected TableTagThSupport getTableTagThSupport() {
		return () -> "操作";
	}

	@Override
	protected TableTagTdSupport getTableTagTdSupport() {
		String contextPath = super.getContextPath();
		StringBuilder sb = new StringBuilder();
		return object -> {
			ResourceVO resourceVO = (ResourceVO) object;
			sb.append("<a href=\"" + contextPath + "/admin/security/resource/edit?id=" + resourceVO.getResourceId() + "\" class=\"btn btn-xs btn-warning\">编辑</a>");
			sb.append("\t\n");
			sb.append("<a href=\"javascript:void(0);\" class=\"btn btn-xs btn-danger\">删除</a>");
			sb.append("\t\n");
			String href = contextPath + "/admin/security/resource/remove?id= "+ resourceVO.getResourceId();
			sb.append(HtmlElementUtils.convertScript("dialog.deleteConfirm("+ href +")"));
			return sb.toString();
		};
	}

}
