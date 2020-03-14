package wang.miansen.roothub.common.ui;

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
	 * 数据总量，默认是100条
	 */
	private String totalRow;
	
	/**
	 * 每页显示的数据量，默认是25条
	 */
	private String pageSize;
	
	/**
	 * 当前是第几页，默认是第一页
	 */
	private String pageNumber;
	
	/**
	 * 总共有多少页
	 */
	private String totalPage;
	
	/**
	 * 点击按钮时发送的请求链接，默认是 "#"
	 */
	private String url;
	
	/**
	 * 显示的页码数，默认是10页
	 */
	private String numberOfPages;
	
	/**
	 * 分页控件的大小，允许的值：mini，small，normal，large。默认是mini
	 */
	private String size;
	
	/**
	 * 分页控件的对齐方式，允许的值用：left（左对齐），center（居中对齐），right（右对齐）。默认是left
	 */
	private String alignment;
	
	/**
	 * 分页对象
	 * <p>当 totalRow、pageSize 或者 pageNumber 为空时，
	 * 将使用 page.pageNumber、page.pageSize、page.totalPage、page.totalRow 属性作为分页的依据。
	 */
	private Page<?> page;

	/*@Override
	public void release() {
		totalRow = null;
		pageSize = null;
		pageNumber = null;
		url = null;
		numberOfPages = null;
		size = null;
		alignment = null;
		page = null;
		super.release();
	}*/

	@Override
	protected String getBodyContentString(String bodyContent) {
		StringBuilder out = new StringBuilder();
		String div = "<div id=\""+ getId() +"\" class=\"panel-footer\"><ul id=\"paginate\" style=\"margin: 0px;\"></ul></div>";
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
	
	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
		super.vars.put("totalPage", totalPage);
	}

	public String getUrl() {
		if (url == null) {
			url = "#";
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		super.vars.put("url", url);
	}

	public String getNumberOfPages() {
		if (numberOfPages == null) {
			numberOfPages = "10";
		}
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
		super.vars.put("numberOfPages", numberOfPages);
	}
	
	public String getSize() {
		if (size == null) {
			size = "mini";
		}
		return size;
	}

	public void setSize(String size) {
		this.size = size;
		super.vars.put("size", size);
	}

	public String getAlignment() {
		if (alignment == null) {
			alignment = "left";
		}
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
		super.vars.put("alignment", alignment);
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
		String totalPage = getTotalPage();
		String url = getUrl();
		String size = getSize();
		String numberOfPages = getNumberOfPages();
		String alignment = getAlignment();
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
		if (StringUtils.isEmpty(totalPage)) {
			if (page != null) {
				totalPage = String.valueOf(page.getTotalPage());
			} else {
				totalPage = "1";
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("$('#paginate').bootstrapPaginator({currentPage: "+pageNumber+",totalPages: "+totalPage+",size:\""+size+"\",bootstrapMajorVersion: 3,alignment:\""+alignment+"\",numberOfPages:"+numberOfPages+",itemTexts: function (type, page, current) {switch (type) {case \"first\": return \"首页\";case \"prev\": return \"上一页\";case \"next\": return \"下一页\";case \"last\": return \"末页\";case \"page\": return page;}},pageUrl: function(type, page, current) {return \""+url+"pageNumber=\"+page}});");
		return sb.toString();
	}

}
