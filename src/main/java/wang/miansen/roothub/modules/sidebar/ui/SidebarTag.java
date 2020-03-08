package wang.miansen.roothub.modules.sidebar.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.ui.AbstractBaseTag;
import wang.miansen.roothub.common.util.ApplicationContextUtils;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.modules.sidebar.dto.SidebarDTO;
import wang.miansen.roothub.modules.sidebar.model.Sidebar;
import wang.miansen.roothub.modules.sidebar.service.SidebarService;

/**
 * @author miansen.wang
 * @date 2020-03-06
 */
@SuppressWarnings("serial")
public class SidebarTag extends AbstractBaseTag {

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder sb = new StringBuilder();
		List<SidebarDTO> sidebarTree = buildSidebarTree(getAllSidebars(), null);
		sb.append("<aside id=\"" + getId() + "\" class=\"main-sidebar\" style=\"position: fixed\">");
		sb.append("\t\n");
		sb.append("<section class=\"sidebar\">");
		sb.append("\t\n");
		sb.append("<ul class=\"sidebar-menu\">");
		sb.append("\t\n");
		sb.append(buildSidebartreeView(sidebarTree));
		sb.append("\t\n");
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</section>");
		sb.append("\t\n");
		sb.append("</aside>");
		return sb.toString();
	}

	/**
	 * 构建侧边栏树视图
	 * 
	 * @param sidebarTree 侧边栏树
	 * @return
	 */
	private String buildSidebartreeView(List<SidebarDTO> sidebarTree) {
		StringBuilder sb = new StringBuilder();
		// 第一次获得的是一级侧边栏集合，后面传入的是下一级侧边栏的集合
		List<SidebarDTO> nextSidebars = sidebarTree;
		for (SidebarDTO sidebarDTO : nextSidebars) {
			if (CollectionUtils.isNotEmpty(sidebarDTO.getChildrenList())) {
				sb.append("<li id=\"" + sidebarDTO.getSidebarId() + "\" class=\"treeview\">");
			} else {
				sb.append("<li id=\"" + sidebarDTO.getSidebarId() + "\">");
			}
			sb.append("\t\n");
			sb.append("<a href=\"" + getContextPath() + sidebarDTO.getSidebarUrl() + "\" target=\"roothub-iframe\">");
			sb.append("\t\n");
			sb.append("<i class=\"" + sidebarDTO.getSidebarIcon() + "\"></i>");
			sb.append("\t\n");
			sb.append("<span>" + sidebarDTO.getSidebarName() + "</span>");
			sb.append("\t\n");
			if (CollectionUtils.isNotEmpty(sidebarDTO.getChildrenList())) {
				sb.append("<span class=\"pull-right-container\">");
				sb.append("\t\n");
				sb.append("<i class=\"fa fa-angle-left pull-right\"></i>");
				sb.append("\t\n");
				sb.append("</span>");
				sb.append("\t\n");
			}
			sb.append("</a>");
			sb.append("\t\n");
			if (CollectionUtils.isNotEmpty(sidebarDTO.getChildrenList())) {
				sb.append("<ul class=\"treeview-menu\">");
				sb.append("\t\n");
				sb.append(buildSidebartreeView(sidebarDTO.getChildrenList()));
				sb.append("\t\n");
				sb.append("</ul>");
			}
			sb.append("\t\n");
			sb.append("</li>");
		}
		return sb.toString();
	}

	/**
	 * 获取所有的侧边栏集合
	 * 
	 * @return
	 */
	private List<SidebarDTO> getAllSidebars() {
		SidebarService sidebarService = ApplicationContextUtils.getBean(SidebarService.class);
		QueryWrapper<Sidebar> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("sidebar_sort");
		return sidebarService.list(queryWrapper);
	}

	/**
	 * 递归调用，构建侧边栏树
	 * 
	 * @param list 所有的侧边栏集合
	 * @param parent 父级侧边栏
	 * @return
	 */
	private List<SidebarDTO> buildSidebarTree(List<SidebarDTO> list, SidebarDTO parent) {
		List<SidebarDTO> result = new ArrayList<>();
		for (SidebarDTO sidebarDTO : list) {
			if (compareSidebar(sidebarDTO.getParentSidebarDTO(), parent)) {
				// 递归查找下一级侧边栏
				sidebarDTO.setChildrenList(buildSidebarTree(list, sidebarDTO));
				result.add(sidebarDTO);
			}
		}
		return result;
	}

	/**
	 * 比较两个 SidebarDTO 对象是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean compareSidebar(SidebarDTO a, SidebarDTO b) {
		if (a == b) {
			return true;
		}
		if (a != null && b != null) {
			return Objects.equals(a.getSidebarId(), b.getSidebarId());
		}
		return false;
	}

}
