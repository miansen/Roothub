package cn.roothub.bbs.common.dao.register;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:36
 */
public class MybatisMapperRegistry extends MapperRegistry{

    private final Configuration config;

    public MybatisMapperRegistry(Configuration config) {
        super(config);
        this.config = config;
    }
}
