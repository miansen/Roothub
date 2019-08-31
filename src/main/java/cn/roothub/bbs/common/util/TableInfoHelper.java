package cn.roothub.bbs.common.util;

import cn.roothub.bbs.common.dao.metadata.TableInfo;
import org.apache.ibatis.builder.MapperBuilderAssistant;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/31 11:56
 */
public class TableInfoHelper {

    public static synchronized TableInfo initTableInfo (MapperBuilderAssistant builderAssistant, Class<?> modelClass) {
        return new TableInfo();
    }
}
