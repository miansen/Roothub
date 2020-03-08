package wang.miansen.roothub.modules.app.ui;

import wang.miansen.roothub.common.ui.AbstractTableTag;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.modules.app.vo.AppCategoryVO;

/**
 * 应用类别 List 标签
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
@SuppressWarnings("serial")
public class AppCategoryListTag extends AbstractTableTag {

	@Override
	protected TableTagThSupport getTableTagThSupport() {
		return () -> "操作";
	}

	@Override
	protected TableTagTdSupport getTableTagTdSupport() {
		String contextPath = super.getContextPath();
		return object -> {
			AppCategoryVO appCategoryVO = (AppCategoryVO) object;
			StringBuilder sb = new StringBuilder();
			sb.append("<a href=\"" + contextPath + "/admin/app/category/edit?id=" + appCategoryVO.getAppCategoryId() + "\" class=\"btn btn-xs btn-warning\">编辑</a>");
			sb.append("\t\n");
			sb.append("<a href=\"javascript:if(confirm('确定要删除吗？')) location.href='" + contextPath + "/admin/permission/delete?id=" + appCategoryVO.getAppCategoryId() + "'\" class=\"btn btn-xs btn-danger\">删除</a>");
			return sb.toString();
		};
	}

}
