package wang.miansen.roothub.common.ui.taglib;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.ui.util.HtmlElementUtils;
import wang.miansen.roothub.common.util.StringUtils;

/**
 * 分页标签
 * 
 * @author miansen.wang
 * @date 2020-02-29
 * @since 3.0
 */
@SuppressWarnings("serial")
public class PaginateTag extends AbstractBaseTag {
	
	/**
	 * 数据总量
	 */
	private String totalRow;
	
	/**
	 * 每页显示的数据量
	 */
	private String pageSize;
	
	/**
	 * 当前是多少页
	 */
	private String pageNumber;
	
	/**
	 * 请求参数
	 */
	private String requestParma;
	
	/**
	 * 连续显示的分页数
	 */
	private String groups;
	
	/**
	 * 颜色主题
	 */
	private String theme;
	
	/**
	 * 是否开启跳页
	 */
	private String skip;
	
	/**
	 * 分页对象
	 * <p>当 totalRow、pageSize 或者 pageNumber 为空时，
	 * 将使用 page.pageNumber、page.pageSize、page.totalPage、page.totalRow 属性作为分页的依据。
	 */
	private Page<?> page;

	@Override
	public void release() {
		totalRow = null;
		pageSize = null;
		pageNumber = null;
		requestParma = null;
		groups = null;
		theme = null;
		skip = null;
		page = null;
		super.release();
	}

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder out = new StringBuilder();
		String div = HtmlElementUtils.convertDiv(getId(), vars, bodyContent);
		String script = HtmlElementUtils.convertScript(getScriptBody());
		out.append(div);
		out.append("\t\n");
		out.append(script);
		return out.toString();
	}

	public String getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(String totalRow) {
		this.totalRow = totalRow;
		super.vars.put("totalRow", totalRow);
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
		super.vars.put("pageSize", pageSize);
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
		super.vars.put("pageNumber", pageNumber);
	}

	public String geRequestParma() {
		if (requestParma == null) {
			requestParma = "?pageNumber=";
		}
		return requestParma;
	}

	public void setRequestParma(String requestParma) {
		this.requestParma = requestParma;
		super.vars.put("requestParma", requestParma);
	}
	
	public String getGroups() {
		if (groups == null) {
			groups = "5";
		}
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
		super.vars.put("groups", groups);
	}

	public String getTheme() {
		if (theme == null) {
			theme = "#337ab7";
		}
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
		super.vars.put("theme", theme);
	}
	
	public String getSkip() {
		if (skip == null) {
			skip = "false";
		}
		return skip;
	}

	public void setSkip(String skip) {
		this.skip = skip;
		super.vars.put("skip", skip);
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getScriptBody() {
		String totalRow = getTotalRow();
		String pageSize = getPageSize();
		String pageNumber = getPageNumber();
		Page<?> page = getPage();
		if (StringUtils.isEmpty(totalRow)) {
			if (page != null) {
				totalRow = String.valueOf(page.getTotalRow());
			} else {
				totalRow = "100";
			}
		}
		if (StringUtils.isEmpty(pageSize)) {
			if (page != null) {
				pageSize = String.valueOf(page.getPageSize());
			} else {
				pageSize = "25";
			}
		}
		if (StringUtils.isEmpty(pageNumber)) {
			if (page != null) {
				pageNumber = String.valueOf(page.getPageNumber());
			} else {
				pageNumber = "1";
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("layui.use('laypage', function(){");
		sb.append("var laypage = layui.laypage;");
		sb.append("laypage.render({");
		sb.append("elem: '"+ getId() +"',");
		sb.append("count: "+ totalRow +",");
		sb.append("limit:"+ pageSize +",");
		sb.append("curr: "+ pageNumber +",");
		sb.append("groups: "+ getGroups() +",");
		sb.append("theme: '"+ getTheme() +"',");
		sb.append("skip: "+ getSkip() +",");
		sb.append("jump: function(obj, first){");
		sb.append("if(!first){");
		sb.append("location.href = '"+ geRequestParma() +"'+obj.curr;");
		sb.append("}}});});");
		return sb.toString();
	}

}
