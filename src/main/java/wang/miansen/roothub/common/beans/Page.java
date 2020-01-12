package wang.miansen.roothub.common.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 * @author sen
 * 2018年5月6日
 * 下午9:08:03
 * @param <T>
 * TODO
 */
public class Page<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<T> list;                // list result of this page
    private int pageNumber;                // page number 当前页数
    private int pageSize;                // result amount of this page 每页显示多少条数据量
    private long totalPage;                // total page 总共有多少页
    private long totalRow;                // total row 总共有多少条数据量
    
	public Page(List<T> list, int pageNumber, int pageSize, long totalRow) {
		super();
		this.list = list;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRow = totalRow;
        this.totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : (totalRow / pageSize) + 1;
	}
    
	 /**
     * Return list of this page.
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Return page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Return page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Return total page.
     */
    public long getTotalPage() {
        return totalPage;
    }

    /**
     * Return total row.
     */
    public long getTotalRow() {
        return totalRow;
    }

    public boolean isFirstPage() {
        return pageNumber == 1;
    }

    public boolean isLastPage() {
        return pageNumber == totalPage;
    }

	@Override
	public String toString() {
		return "PageDataBody [list=" + list + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", totalRow=" + totalRow + "]";
	}
    
}
