package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import wang.miansen.roothub.common.dao.mapper.util.StringPool;

/**
 * SQL 片段容器的抽象父类，封装了将 SQL 片段添加到容器的方法。
 * 
 * @author miansen.wang
 * @date 2019-9-15 14:27
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractSqlSegmentList extends ArrayList<ISqlSegment> implements ISqlSegment, StringPool {

	/**
	 * 当前容器中的最后一个 SQL 片段
	 */
	protected ISqlSegment lastSqlSegment;

	/**
	 * 将 SQL 片段添加到容器当中
	 * @param c
	 * @return boolean
	 */
	public boolean addAll(Collection<? extends ISqlSegment> c) {
		List<ISqlSegment> sqlSegmentList = new ArrayList<>(c);
		boolean goon = transformList(sqlSegmentList);
		if (goon) {
			flushLastSqlSegment(sqlSegmentList);
		}
		super.addAll(sqlSegmentList);
		return true;
	}

	/**
	 * 在添加 sql 片段到容器前，根据具体的场景做一些前置操作。（由子类实现）
	 * @param sqlSegmentList
	 * @return boolean
	 */
	protected abstract boolean transformList(List<ISqlSegment> sqlSegmentList);

	/**
	 * 刷新最后一个 SQL 片段
	 * @param list
	 */
	protected void flushLastSqlSegment(List<? extends ISqlSegment> list) {
		int size = list.size();
		if (size > 0) {
			lastSqlSegment = list.get(size - 1);
		}
	}
}
