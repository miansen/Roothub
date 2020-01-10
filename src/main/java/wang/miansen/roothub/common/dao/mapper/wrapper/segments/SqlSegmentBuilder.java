package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import java.util.Arrays;
import java.util.List;

import wang.miansen.roothub.common.dao.mapper.enums.MatchSegment;

/**
 * sql 片段生成器，提供了 add 方法，可以将 sql 片段存储到对应的容器当中。
 * <p>还实现了 getSqlSegment 方法，可以通过此方法获取到完整的 sql 语句。
 * 
 * @author miansen.wang
 * @date 2019-10-3 17:29
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SqlSegmentBuilder implements ISqlSegment {

	private final NormalSqlSegmentList normal = new NormalSqlSegmentList();

	private final GroupBySqlSegmentList groupBy = new GroupBySqlSegmentList();

	private final HavingSqlSegmentList having = new HavingSqlSegmentList();

	private final OrderBySqlSegmentList orderBy = new OrderBySqlSegmentList();

	private final LimitSqlSegmentList limit = new LimitSqlSegmentList();

	/**
	 * 根据第一个 sql 片段，匹配到对应的容器。
	 * @param sqlSegments
	 */
	public void add(ISqlSegment... sqlSegments) {
		List<ISqlSegment> sqlSegmentList = Arrays.asList(sqlSegments);
		ISqlSegment firstSqlSegment = sqlSegmentList.get(0);
		if (MatchSegment.ORDER_BY.match(firstSqlSegment)) {
			orderBy.addAll(sqlSegmentList);
		} else if (MatchSegment.HAVING.match(firstSqlSegment)) {
			having.addAll(sqlSegmentList);
		} else if (MatchSegment.GROUP_BY.match(firstSqlSegment)) {
			groupBy.addAll(sqlSegmentList);
		} else if (MatchSegment.LIMIT.match(firstSqlSegment)) {
			limit.addAll(sqlSegmentList);
		} else {
			normal.addAll(sqlSegmentList);
		}
	}

	/**
	 * 组装各个容器的 sql 片段，返回完整的 sql 语句。
	 * <p>包含 where... group by... having... order by... limit...
	 * @return
	 */
	@Override
	public String getSqlSegment() {
		return normal.getSqlSegment() + groupBy.getSqlSegment() + having.getSqlSegment() + orderBy.getSqlSegment()
				+ limit.getSqlSegment();
	}
	
	/**
	 * 返回普通的 sql 语句。
	 * <p>只包含 where...
	 * @return
	 */
	public String getNormalSqlSegment() {
		return normal.getSqlSegment();
	}
	
}
