package wang.miansen.roothub.modules.system.mapper;

import java.util.List;

import wang.miansen.roothub.modules.system.bo.SystemConfigBO;
import wang.miansen.roothub.modules.system.entity.SystemConfigDO;

/**
 * BO 转 DO
 *
 * @author miansen.wang
 * @since 2021-04-18 17:10
 */
public interface Bo2DoMapper {

    /**
     * SystemConfigBO 转 SystemConfigDO
     *
     * @param systemConfigBo SystemConfigBO
     * @return SystemConfigDO
     */
    SystemConfigDO systemConfigBo2Do(SystemConfigBO systemConfigBo);

    /**
     * SystemConfigBO List 转 SystemConfigDO List
     *
     * @param systemConfigBoList SystemConfigBO List
     * @return SystemConfigDO List
     */
    List<SystemConfigDO> systemConfigBoList2DoList(List<SystemConfigBO> systemConfigBoList);
}
