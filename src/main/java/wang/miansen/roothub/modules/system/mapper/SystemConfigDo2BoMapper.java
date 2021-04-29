package wang.miansen.roothub.modules.system.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import wang.miansen.roothub.modules.system.bo.SystemConfigBO;
import wang.miansen.roothub.modules.system.entity.SystemConfigDO;

/**
 * DO 转 BO
 *
 * @author miansen.wang
 * @since 2021-04-18 17:02
 */
@Mapper(componentModel = "spring")
public interface SystemConfigDo2BoMapper {

    /**
     * SystemConfigDO 转 SystemConfigBO
     *
     * @param systemConfigDo SystemConfigDO
     * @return SystemConfigBO
     */
    SystemConfigBO systemConfigDo2Bo(SystemConfigDO systemConfigDo);

    /**
     * SystemConfigDO List 转 SystemConfigBO List
     *
     * @param systemConfigDoList SystemConfigDO List
     * @return SystemConfigBO List
     */
    List<SystemConfigBO> systemConfigDoList2BoList(List<SystemConfigDO> systemConfigDoList);
}
