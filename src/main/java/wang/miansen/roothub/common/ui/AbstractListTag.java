package wang.miansen.roothub.common.ui;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.ui.exception.BaseTagException;
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
public abstract class AbstractListTag extends AbstractBaseTag {

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
	
	/**
	 * 是否显示复选框，默认是 false。
	 */
	private String checkbox;

	@Override
	public void release() {
		page = null;
		th = null;
		td = null;
		row = null;
		checkbox = null;
		super.release();
	}

	@Override
	protected String getBodyContentString(String bodyContent) throws BaseTagException {
		try {
			StringBuilder out = new StringBuilder();
			String table = HtmlElementUtils.convertTable(getId(), page, vars, getTableTagThSupport(),
					getTableTagTdSupport());
			out.append(table);
			return out.toString();
		} catch (Exception e) {
			logger.error(getClass().getName() + " getBodyContentString error: " + e);
			throw new BaseTagException(500, getClass().getName() + " getBodyContentString error: " + e, e.getMessage());
		}
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
	
	public String getCheckbox() {
		if (checkbox == null) {
			checkbox = "false";
		}
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
		super.vars.put("checkbox", checkbox);
	}

	/**
	 * 当默认的 list 标签无法满足业务要求时，需要额外的增加一条或者多条 th，那么子类可以
	 * 实现该方法
	 * 
	 * @return TableTagThSupport
	 */
	protected abstract TableTagThSupport getTableTagThSupport();

	/**
	 * 当默认的 list 标签无法满足业务要求时，需要额外的增加一条或者多条 td，那么子类可以
	 * 实现该方法
	 * 
	 * @return TableTagTdSupport
	 */
	protected abstract TableTagTdSupport getTableTagTdSupport();


}
