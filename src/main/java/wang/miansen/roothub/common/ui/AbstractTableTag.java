package wang.miansen.roothub.common.ui;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;
import wang.miansen.roothub.common.ui.util.HtmlElementUtils;

/**
 * 表格标签抽象父类
 * 
 * @author miansen.wang
 * @date 2020-02-29
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractTableTag extends AbstractBaseTag {

	/**
	 * 分页对象
	 * <p>迭代其中的 {@code list} 对象，从而填充表格的 th 和 td。
	 * <p>其中 pageNumber、pageSize、totalPage、totalRow 属性作为分页的依据。
	 */
	private Page<?> page;

	/**
	 * 表头的名字，多个的话用 ";" 号分割。
	 * <p>注意：如果为空的话，将默认使用字段名作为表头的名字。
	 */
	private String th;

	/**
	 * 单元格的属性名，根据属性名从 {@code page} 中获取属性值。
	 * <p>注意：属性名必须和字段名一样，否则会抛出异常。如果为空的话，将默认使用所有字段的值作为单元格的内容。
	 */
	private String td;

	/**
	 * 是否显示行号，默认是 false。
	 */
	private String row;

	@Override
	public void release() {
		page = null;
		th = null;
		td = null;
		row = null;
		super.release();
	}

	@Override
	protected String getBodyContentString(String bodyContent) {
		try {
			StringBuilder out = new StringBuilder();
			String table = HtmlElementUtils.convertTable(getId(), page, vars, getTableTagThSupport(),
					getTableTagTdSupport());
			out.append(table);
			return out.toString();
		} catch (Exception e) {
			logger.error(getClass().getName() + " getBodyContentString error: " + e);
		}
		return "";
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getTh() {
		return th;
	}

	public void setTh(String th) {
		this.th = th;
		super.vars.put("th", th);
	}

	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
		super.vars.put("td", td);
	}

	public String getRow() {
		if (row == null) {
			row = "false";
		}
		return row;
	}

	public void setRow(String row) {
		this.row = row;
		super.vars.put("row", row);
	}

	protected abstract TableTagThSupport getTableTagThSupport();


	protected abstract TableTagTdSupport getTableTagTdSupport();


}
