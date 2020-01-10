package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import java.util.List;
import java.util.stream.Collectors;

import wang.miansen.roothub.common.dao.mapper.enums.MatchSegment;
import wang.miansen.roothub.common.dao.mapper.enums.SqlKeyword;

/**
 * 普通的 SQL 片段容器
 * <p>只有 AND 、OR 、LIKE、EXISTS 、NOT EXISTS 这些简单的连接词
 * <p>没有 GROUP BY 、HAVING 、ORDER BY 等复杂的关键词
 *
 * @author miansen.wang
 * @date 2019-9-15 16:12
 * @since 3.0
 */
@SuppressWarnings("serial")
public class NormalSqlSegmentList extends AbstractSqlSegmentList {

	/**
	 * 根据第一个和最后一个 SQL 片段判断是否需要 AND 连接
	 * @param sqlSegmentList
	 * @return boolean
	 */
	@Override
	protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
		ISqlSegment firstSqlSegment = sqlSegmentList.get(0);
		// 如果第一个和最后一个 SQL 片段不是 "AND" 或者 "OR"，则默认用 AND 连接
		if (lastSqlSegment != null && !MatchSegment.ADN_OR.match(lastSqlSegment)
				&& !MatchSegment.ADN_OR.match(firstSqlSegment)) {
			super.add(SqlKeyword.AND);
		}
		return true;
	}

	/**
	 * 返回拼接好的 SQL 语句
	 * @return
	 */
	@Override
	public String getSqlSegment() {
		return this.isEmpty() ? "1=1"
				: this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE));
	}
	
}
