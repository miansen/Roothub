package wang.miansen.roothub.common.ui.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.util.ReflectionUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.role.vo.RoleVO;

/**
 * HTML 元素工具类
 * 
 * @author miansen.wang
 * @date 2020-02-29
 * @since 3.0
 */
public abstract class HtmlElementUtils {

	public static String convertDiv(String id, Map<String, String> vars, String body) {
		String clazz = vars.get("class");
		String type = vars.get("type");
		String style = vars.get("style");
		String attr = vars.get("attr");
		StringBuilder sb = new StringBuilder();
		sb.append("<div");
		if (StringUtils.notEmpty(id)) {
			sb.append(" id=\"").append(id).append("\"");
		}
		if (StringUtils.notEmpty(clazz)) {
			sb.append(" class=\"").append(clazz).append("\"");
		}
		if (StringUtils.notEmpty(type)) {
			sb.append(" type=\"").append(type).append("\"");
		}
		if (StringUtils.notEmpty(style)) {
			sb.append(" style=\"").append(style).append("\"");
		}
		if (StringUtils.notEmpty(attr)) {
			sb.append(" attr=\"").append(attr).append("\"");
		}
		sb.append(">");
		if (StringUtils.notEmpty(body)) {
			sb.append("\t\n");
			sb.append(body);
			sb.append("\t\n");
		}
		sb.append("</div>");
		return sb.toString();
	}

	public static String convertTable(String id, Page<?> page, Map<String, String> vars,
			TableTagThSupport tableTagThSupport, TableTagTdSupport tableTagTdSupport) throws Exception {
		String clazz = vars.get("class");
		String type = vars.get("type");
		String style = vars.get("style");
		String attr = vars.get("attr");
		String[] ths = StringUtils.notEmpty(vars.get("th")) ? vars.get("th").split(";") : null;
		String[] tds = StringUtils.notEmpty(vars.get("td")) ? vars.get("td").split(";") : null;
		String row = vars.get("row");
		List<?> list = page.getList();
		StringBuilder sb = new StringBuilder();
		sb.append("<table");
		if (StringUtils.notEmpty(id)) {
			sb.append(" id=\"").append(id).append("\"");
		}
		if (StringUtils.notEmpty(clazz)) {
			sb.append(" class=\"").append(clazz).append("\"");
		}
		if (StringUtils.notEmpty(type)) {
			sb.append(" type=\"").append(type).append("\"");
		}
		if (StringUtils.notEmpty(style)) {
			sb.append(" style=\"").append(style).append("\"");
		}
		if (StringUtils.notEmpty(attr)) {
			sb.append(" attr=\"").append(attr).append("\"");
		}
		sb.append(">");
		if (CollectionUtils.isEmpty(list)) {
			sb.append("</table>");
			return sb.toString();
		}
		sb.append("\t\n");
		sb.append("<thead>");
		sb.append("\t\n");
		sb.append("<tr>");
		sb.append("\t\n");
		if (StringUtils.notEmpty(row) && Objects.equals(row, "true")) {
			sb.append("<th>#</th>");
			sb.append("\t\n");
		}
		if (ths != null && ths.length > 0) {
			for (String th : ths) {
				sb.append("<th>" + th + "</th>");
				sb.append("\t\n");
			}
		} else {
			Object object = list.get(0);
			List<Field> fieldList = ReflectionUtils.getFieldList(object.getClass());
			for (int i = 0; i < fieldList.size(); i++) {
				Field field = fieldList.get(i);
				sb.append("<th>" + field.getName() + "</th>");
				sb.append("\t\n");
			}
		}
		if (tableTagThSupport != null) {
			sb.append("<th>" + tableTagThSupport.apply() + "</th>");
			sb.append("\t\n");
		}
		sb.append("</tr>");
		sb.append("\t\n");
		sb.append("</thead>");
		sb.append("\t\n");
		sb.append("<tbody>");
		sb.append("\t\n");
		for (int j = 0; j < list.size(); j++) {
			sb.append("<tr>");
			sb.append("\t\n");
			if (StringUtils.notEmpty(row) && Objects.equals(row, "true")) {
				int rowNum = j + 1;
				sb.append("<td>" + rowNum + "</td>");
				sb.append("\t\n");
			}
			Object object = list.get(j);
			if (tds != null && tds.length > 0) {
				for (String td : tds) {
					Method readMethod = ReflectionUtils.getReadMethod(td, object.getClass());
					Object result = readMethod.invoke(object);
					if (result != null) {
						sb.append("<td>" + result.toString() + "</td>");
					} else {
						sb.append("<td></td>");
					}
					sb.append("\t\n");
				}
			} else {
				List<Field> fieldList = ReflectionUtils.getFieldList(object.getClass());
				for (Field field : fieldList) {
					String fieldName = field.getName();
					Method readMethod = ReflectionUtils.getReadMethod(fieldName, object.getClass());
					Object result = readMethod.invoke(object);
					if (result != null) {
						sb.append("<td>" + result.toString() + "</td>");
					} else {
						sb.append("<td></td>");
					}
					sb.append("\t\n");
				}
			}
			if (tableTagTdSupport != null) {
				sb.append("<td>" + tableTagTdSupport.apply(object) + "</td>");
				sb.append("\t\n");
			}
			sb.append("</tr>");
			sb.append("\t\n");
		}
		sb.append("</tbody>");
		sb.append("\t\n");
		sb.append("</table>");
		return sb.toString();
	}

	public static String convertScript(String body) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">");
		if (StringUtils.notEmpty(body)) {
			sb.append("\t\n");
			sb.append(body);
			sb.append("\t\n");
		}
		sb.append("</script>");
		return sb.toString();
	}
	
	public static TableTagThSupport th(String name) {
		return () -> name;
	}
	
	public static TableTagTdSupport td(String name) {
		return object -> name;
	}
	

	public static void main(String[] args) throws Exception {
		List<RoleVO> list = new ArrayList<RoleVO>();
		RoleVO roleVO1 = new RoleVO();
		roleVO1.setRoleId(IDGenerator.generateID());
		roleVO1.setRoleName("role1");
		roleVO1.setRoleDesc("role desc1");
		roleVO1.setCreateDate("2021");
		roleVO1.setUpdateDate("2021");

		RoleVO roleVO2 = new RoleVO();
		roleVO2.setRoleId(IDGenerator.generateID());
		roleVO2.setRoleName("role2");
		roleVO2.setRoleDesc("role desc2");
		roleVO2.setCreateDate("2022");
		roleVO2.setUpdateDate("2022");

		RoleVO roleVO3 = new RoleVO();
		roleVO3.setRoleId(IDGenerator.generateID());
		roleVO3.setRoleName("role3");
		roleVO3.setRoleDesc("role desc3");
		roleVO3.setCreateDate("2023");
		roleVO3.setUpdateDate("2023");
		list.add(roleVO1);
		list.add(roleVO2);
		list.add(roleVO3);
		Page<RoleVO> page = new Page<>(list, 1, 25, 100);
		Map<String, String> vars = new HashMap<>();
		vars.put("th", "角色ID;角色名称;角色描述;创建时间;更新时间");
		vars.put("td", "roleId;roleName;roleDesc;createDate;updateDate");
		vars.put("row", "true");
		String table = convertTable("123", page, vars, th("操作"), td("删除"));
		System.out.println(table);
	}
}
