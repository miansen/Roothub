package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.util.StringPool;
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
     * 最后一个 SQL 片段，用于判断添加连接条件
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
     * 由子类实现，根据具体的场景做转换
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
        if (size > 1) {
            lastSqlSegment = list.get(size - 1);
        }
    }
}
