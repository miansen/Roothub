package wang.miansen.roothub.modules.menu.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.ui.AbstractBaseTag;
import wang.miansen.roothub.common.util.ApplicationContextUtils;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.modules.menu.dto.MenuDTO;
import wang.miansen.roothub.modules.menu.model.Menu;
import wang.miansen.roothub.modules.menu.service.MenuService;

/**
 * 左侧边栏标签
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
@SuppressWarnings("serial")
public class MainSidebarTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		List<MenuDTO> sidebarTree = buildMenuTree(getAllMenuList(), null);
		sb.append("<aside class=\"main-sidebar\">");
		sb.append("\t\n");
		sb.append("<section class=\"sidebar\">");
		sb.append("\t\n");
		// 用户面板开始
		sb.append("<div class=\"user-panel\">");
		sb.append("\t\n");
		sb.append("<div class=\"pull-left image\">");
		sb.append("\t\n");
		sb.append("<img src=\"dist/img/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("<div class=\"pull-left info\">");
		sb.append("\t\n");
		sb.append("<p>Alexander Pierce</p>");
		sb.append("\t\n");
		sb.append("<a href=\"#\"><i class=\"fa fa-circle text-success\"></i> Online</a>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		// 用户面板结束
		
		// 搜索框开始
		sb.append("\t\n");
		sb.append("<form action=\"#\" method=\"get\" class=\"sidebar-form\">");
		sb.append("\t\n");
		sb.append("<div class=\"input-group\">");
		sb.append("\t\n");
		sb.append("<input type=\"text\" name=\"q\" class=\"form-control\" placeholder=\"Search...\">");
		sb.append("\t\n");
		sb.append("<span class=\"input-group-btn\">");
		sb.append("\t\n");
		sb.append("<button type=\"submit\" name=\"search\" id=\"search-btn\" class=\"btn btn-flat\"><i class=\"fa fa-search\"></i></button>");
		sb.append("\t\n");
		sb.append("</span>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</form>");
		// 搜索框结束
		
		// 菜单开始
		sb.append("<ul class=\"sidebar-menu\" data-widget=\"tree\">");
		sb.append("\t\n");
		sb.append(buildMenutreeView(sidebarTree));
		sb.append("\t\n");
		sb.append("</ul>");
		// 菜单结束
		
		sb.append("\t\n");
		sb.append("</section>");
		sb.append("\t\n");
		sb.append("</aside>");
		return sb.toString();
	}

	/**
	 * 递归调用，构建菜单树视图
	 * 
	 * @param menuTree 菜单树
	 * @return
	 */
	private String buildMenutreeView(List<MenuDTO> menuTree) {
		StringBuilder sb = new StringBuilder();
		// 第一次获得的是一级菜单集合，后面传入的是下一级菜单集合
		List<MenuDTO> nextMenuList = menuTree;
		for (MenuDTO menuDTO : nextMenuList) {
			if (CollectionUtils.isNotEmpty(menuDTO.getChildrenMenuDTOList())) {
				sb.append("<li id=\"" + menuDTO.getMenuId() + "\" class=\"treeview\">");
			} else {
				sb.append("<li id=\"" + menuDTO.getMenuId() + "\">");
			}
			sb.append("\t\n");
			sb.append("<a href=\"" + getContextPath() + menuDTO.getMenuUrl() + "\" target=\"roothub-iframe\">");
			sb.append("\t\n");
			sb.append("<i class=\"" + menuDTO.getMenuIcon() + "\"></i>");
			sb.append("\t\n");
			sb.append("<span>" + menuDTO.getMenuName() + "</span>");
			sb.append("\t\n");
			if (CollectionUtils.isNotEmpty(menuDTO.getChildrenMenuDTOList())) {
				sb.append("<span class=\"pull-right-container\">");
				sb.append("\t\n");
				sb.append("<i class=\"fa fa-angle-left pull-right\"></i>");
				sb.append("\t\n");
				sb.append("</span>");
				sb.append("\t\n");
			}
			sb.append("</a>");
			sb.append("\t\n");
			if (CollectionUtils.isNotEmpty(menuDTO.getChildrenMenuDTOList())) {
				sb.append("<ul class=\"treeview-menu\">");
				sb.append("\t\n");
				sb.append(buildMenutreeView(menuDTO.getChildrenMenuDTOList()));
				sb.append("\t\n");
				sb.append("</ul>");
			}
			sb.append("\t\n");
			sb.append("</li>");
		}
		return sb.toString();
	}

	/**
	 * 获取所有的菜单
	 * 
	 * @return
	 */
	private List<MenuDTO> getAllMenuList() {
		MenuService menuService = ApplicationContextUtils.getBean(MenuService.class);
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("menu_sort");
		return menuService.list(queryWrapper);
	}

	/**
	 * 递归调用，构建菜单树
	 * 
	 * @param list 所有的菜单集合
	 * @param parent 父级菜单
	 * @return
	 */
	private List<MenuDTO> buildMenuTree(List<MenuDTO> list, MenuDTO parent) {
		List<MenuDTO> result = new ArrayList<>();
		for (MenuDTO menuDTO : list) {
			if (compareMenu(menuDTO.getParentMenuDTO(), parent)) {
				// 递归查找下一级菜单
				menuDTO.setChildrenMenuDTOList(buildMenuTree(list, menuDTO));
				result.add(menuDTO);
			}
		}
		return result;
	}

	/**
	 * 比较两个菜单对象是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean compareMenu(MenuDTO a, MenuDTO b) {
		if (a == b) {
			return true;
		}
		if (a != null && b != null) {
			return Objects.equals(a.getMenuId(), b.getMenuId());
		}
		return false;
	}

}
