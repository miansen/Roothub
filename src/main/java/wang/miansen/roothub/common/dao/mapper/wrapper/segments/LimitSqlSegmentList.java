package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Limit 容器
 * 
 * @author miansen.wang
 * @date 2020-01-05
 * @since 3.0
 */
@SuppressWarnings("serial")
public class LimitSqlSegmentList extends AbstractSqlSegmentList {

	@Override
	protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
		// 如果不为空，说明已经有 LIMIT 语句了，需要把之前的给清空。
		if (!this.isEmpty()) {
			this.clear();
		}
		return true;
	}

	@Override
	public String getSqlSegment() {
		return this.isEmpty() ? EMPTY
				: this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE, SPACE, EMPTY));
	}

}
