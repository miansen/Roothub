package cn.roothub.bbs.common.dao.wrapper.query;

import cn.roothub.bbs.common.dao.builder.TableInfoBuilder;
import cn.roothub.bbs.common.dao.exceptions.BaseMapperException;
import cn.roothub.bbs.common.dao.metadata.TableFieldInfo;
import cn.roothub.bbs.common.dao.wrapper.AbstractWrapper;
import cn.roothub.bbs.common.dao.util.ArrayUtils;
import cn.roothub.bbs.common.dao.util.StringPool;
import cn.roothub.bbs.module.tag.model.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @param <T> 数据库表映射实体类
 *
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:50
 */
public class QueryWrapper<T> extends AbstractWrapper<T, QueryWrapper<T>, String, Object> implements Query<T, QueryWrapper<T>, String>{

    private String selectColumns;

    public QueryWrapper() {

    }

    public QueryWrapper(Class<T> modelClass) {
        super.modelClass = modelClass;
    }

    public QueryWrapper(String... columns) {
        this.select(columns);
    }

    public QueryWrapper(Class<T> modelClass, String... columns) {
        super.modelClass = modelClass;
        this.select(columns);
    }

    @Override
    public QueryWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.selectColumns = String.join(StringPool.SPACE_COMMA_SPACE, columns);
        }
        return super.typedThis;
    }

    @Override
    public QueryWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        if (super.modelClass == null) {
            throw new BaseMapperException("modelClass must not null,please set modelClass before use this method!");
        }
        return select(super.modelClass, predicate);
    }

    @Override
    public QueryWrapper<T> select(Class<T> modelClass, Predicate<TableFieldInfo> predicate) {
        super.modelClass = modelClass;
        this.selectColumns = TableInfoBuilder.getTableInfo(modelClass).getTableFieldInfoList().stream().filter(predicate)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(StringPool.SPACE_COMMA_SPACE));
        return super.typedThis;
    }

    public String getSelectColumns() {
        return selectColumns;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 15);
        map.put("create_date", 2019);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>(Tag.class);
        queryWrapper = queryWrapper.eq("tagName", "zhangsan").ne("id",12).eq(map);
        System.out.println(queryWrapper.getSqlSegment());
        queryWrapper = queryWrapper.eq(map);
        System.out.println(queryWrapper.getSqlSegment());
        QueryWrapper<Tag> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2 = queryWrapper2.eq("id", 1)
                .ne("name", "zhangsan")
                .ge(map)
                .between("create_date", "2018", "2019")
                .notBetween("update_date", "2019", "2020")
                .isNull("sex")
                .isNotNull("addr")
                .in("last_name", "a", "b", "c")
                .in("last_name")
                .notIn("first_name", "d", "e", "f")
                .notIn("first_name")
                .like("title", "i like sql")
                .notLike("title", "i like")
                .likeLeft("title", "like")
                .likeRight("title", "like");
        System.out.println(queryWrapper2.getSqlSegment());
        Map<String, Object> paramNameValuePairs1 = queryWrapper.paramNameValuePairs;
        Map<String, Object> paramNameValuePairs2 = queryWrapper2.paramNameValuePairs;
        QueryWrapper<Object> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("id", 1)
                .and()
                .ne("name", "zhangsan")
                .or()
                .ge("addr", "china")
                .exists("select 1 from user b where a.id = b.id")
                .groupBy("id", "name", "age")
                .groupBy("addr")
                .having("count(1) > 1")
                .orderByDesc("id", "id2", "id3")
                .orderByAsc("name")
                .orderBy(false, "age");
        String sqlSegment3 = queryWrapper3.getSqlSegment();
        System.out.println(sqlSegment3);

        queryWrapper.select("id","name","age");
        queryWrapper.select(i -> !i.getColumn().equals("name"));
        String selectColumns = queryWrapper.getSelectColumns();
        System.out.println(selectColumns);
    }
}
