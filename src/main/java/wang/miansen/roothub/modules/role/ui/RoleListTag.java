package wang.miansen.roothub.modules.role.ui;

import javax.servlet.http.HttpServletRequest;

import wang.miansen.roothub.common.ui.AbstractListTag;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.modules.role.vo.RoleVO;

/**
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class RoleListTag extends AbstractListTag {

	@Override
	protected TableTagThSupport getTableTagThSupport() {
		return () -> "操作";
	}

	@Override
	protected TableTagTdSupport getTableTagTdSupport() {
		HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
		String contextPath = request.getContextPath();
		return object -> {
			RoleVO roleVO = (RoleVO) object;
			return "<a href=\"" + contextPath + "/admin/role/edit?id=" + roleVO.getRoleId()
					+ "\" class=\"btn btn-xs btn-warning\">编辑</a>\n<a href=\"javascript:if(confirm('确定要删除吗？')) location.href='"
					+ contextPath + "/admin/role/remove?id=" + roleVO.getRoleId()
					+ "'\" class=\"btn btn-xs btn-danger\">删除</a>";
		};
	}

}
