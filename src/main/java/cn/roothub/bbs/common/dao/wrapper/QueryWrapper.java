package cn.roothub.bbs.common.dao.wrapper;

import cn.roothub.bbs.module.tag.model.Tag;

/**
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:50
 */
public class QueryWrapper<T> extends AbstractWrapper<T, QueryWrapper<T>, String, Object>{

    public static void main(String[] args) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("tagName", "zhangsan").ne("id",12);
        System.out.println(queryWrapper.getSqlSegment());
    }
}
