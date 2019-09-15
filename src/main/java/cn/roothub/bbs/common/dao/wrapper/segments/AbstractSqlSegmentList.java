package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.enums.SqlKeyword;
import cn.roothub.bbs.common.dao.wrapper.ISqlSegment;
import cn.roothub.bbs.common.util.StringPool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 抽象的 SQL 片段集合
 * @Author: miansen.wang
 * @Date: 2019/9/15 14:27
 */
public abstract class AbstractSqlSegmentList extends ArrayList<ISqlSegment> implements ISqlSegment, StringPool{

    /**
     * 最后一个 SQL 片段，用于判断是否添加连接条件（AND、OR、EXISTS、NOT EXISTS）
     */
    protected ISqlSegment lastSqlSegment;

    /**
     * 将 SQL 片段添加到容器当中
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends ISqlSegment> c) {
        List<ISqlSegment> list = new ArrayList<>(c);
        if (this.lastSqlSegment != null) {
            super.add(SqlKeyword.AND);
        }
        this.flushLastSqlSegment(list);
        super.addAll(list);
        return true;
    }

    /**
     * 刷新最后一个 SQL 片段
     * @param list
     */
    protected void flushLastSqlSegment(List<? extends ISqlSegment> list) {
        this.lastSqlSegment = list.get(list.size() - 1);
    }
}
