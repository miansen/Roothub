package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * 根据 ID 更新一条数据
 * @Author: miansen.wang
 * @Date: 2019/11/2 23:47
 */
public class UpdateById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        return null;
    }
}
