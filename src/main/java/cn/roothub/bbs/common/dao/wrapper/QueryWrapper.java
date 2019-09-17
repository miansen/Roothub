package cn.roothub.bbs.common.dao.wrapper;

import cn.roothub.bbs.module.tag.model.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:50
 */
public class QueryWrapper<T> extends AbstractWrapper<T, QueryWrapper<T>, String, Object>{

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 15);
        map.put("create_date", 2019);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("tagName", "zhangsan").ne("id",12).eq(map);
        System.out.println(queryWrapper.getSqlSegment());
        queryWrapper = queryWrapper.eq(map);
        System.out.println(queryWrapper.getSqlSegment());
        QueryWrapper<Tag> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2 = queryWrapper2.eq("id", 1).ne("name", "zhangsan").ge(map);
        System.out.println(queryWrapper2.getSqlSegment());
    }
}
