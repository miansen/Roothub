package wang.miansen.roothub.security.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import wang.miansen.roothub.security.bo.SecPermissionBO;
import wang.miansen.roothub.security.bo.SecResourceBO;
import wang.miansen.roothub.security.bo.SecRoleBO;
import wang.miansen.roothub.security.bo.SecSystemBO;
import wang.miansen.roothub.security.entity.SecPermissionDO;
import wang.miansen.roothub.security.entity.SecResourceDO;
import wang.miansen.roothub.security.entity.SecRoleDO;
import wang.miansen.roothub.security.entity.SecSystemDO;

/**
 * DO 转 BO
 *
 * @author miansen.wang
 * @date 2020-12-27 15:21
 */
@Mapper(componentModel = "spring")
public interface SecDo2BoMapper {

    /**
     * RoleDO 转 RoleBO
     * @param roleDo roleDo
     * @return RoleBO
     */
    SecRoleBO roleDo2Bo(SecRoleDO roleDo);

    /**
     * PermissionDO 转 PermissionBO
     *
     * @param permissionDo permissionDo
     * @return PermissionBO
     */
    SecPermissionBO permissionDo2Bo(SecPermissionDO permissionDo);

    /**
     * ResourceDO 转 ResourceBO
     *
     * @param resourceDo resourceDo
     * @return ResourceBO
     */
    SecResourceBO resourceDo2Bo(SecResourceDO resourceDo);

    /**
     * SystemDO 转 SystemBO
     *
     * @param systemDo systemDo
     * @return SystemBO
     */
    SecSystemBO systemDo2Bo(SecSystemDO systemDo);

    /**
     * List<RoleDO> 转 List<RoleBO>
     *
     * @param roleDoList roleDo List
     * @return roleBo List
     */
    List<SecRoleBO> roleDoList2BoList(List<SecRoleDO> roleDoList);

    /**
     * List<ResourceDO> 转 List<ResourceBO>
     *
     * @param resourceDoList resourceDo List
     * @return resourceBo List
     */
    List<SecResourceBO> resourceDoList2BoList(List<SecResourceDO> resourceDoList);

    /**
     * PermissionDO List 转 PermissionBO List
     *
     * @param permissionDoList permissionDo List
     * @return permissionBo List
     */
    List<SecPermissionBO> permissionDoList2BoList(List<SecPermissionDO> permissionDoList);

    /**
     * SystemDO List 转 SystemBO List
     *
     * @param systemDoList SystemDO List
     * @return SystemBO List
     */
    List<SecSystemBO> systemDoList2BoList(List<SecSystemDO> systemDoList);
}
