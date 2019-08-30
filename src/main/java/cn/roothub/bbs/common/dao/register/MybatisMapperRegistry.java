package cn.roothub.bbs.common.dao.register;

import org.apache.ibatis.session.Configuration;
import java.util.List;

/**
 * 为 IBaseMapper 的导出接口注入通用的 SQL
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:36
 */
public class MybatisMapperRegistry {

    private Configuration config;

    public MybatisMapperRegistry(Configuration config) {
        this.config = config;
    }

    public void addMappers(List<Class<?>> mappers) {

    }

}
