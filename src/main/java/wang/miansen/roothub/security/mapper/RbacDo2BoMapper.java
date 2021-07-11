package wang.miansen.roothub.security.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import wang.miansen.roothub.security.bo.PermissionBO;
import wang.miansen.roothub.security.bo.ResourceBO;
import wang.miansen.roothub.security.bo.RoleBO;
import wang.miansen.roothub.security.bo.SystemBO;
import wang.miansen.roothub.security.entity.PermissionDO;
import wang.miansen.roothub.security.entity.ResourceDO;
import wang.miansen.roothub.security.entity.RoleDO;
import wang.miansen.roothub.security.entity.SystemDO;

/**
 * DO 转 BO
 *
 * @author miansen.wang
 * @date 2020-12-27 15:21
 */
@Mapper(componentModel = "spring")
public interface RbacDo2BoMapper {

    /**
     * RoleDO 转 RoleBO
     * @param roleDo roleDo
     * @return RoleBO
     */
    RoleBO roleDo2Bo(RoleDO roleDo);

    /**
     * PermissionDO 转 PermissionBO
     *
     * @param permissionDo permissionDo
     * @return PermissionBO
     */
    PermissionBO permissionDo2Bo(PermissionDO permissionDo);

    /**
     * ResourceDO 转 ResourceBO
     *
     * @param resourceDo resourceDo
     * @return ResourceBO
     */
    ResourceBO resourceDo2Bo(ResourceDO resourceDo);

    /**
     * SystemDO 转 SystemBO
     *
     * @param systemDo systemDo
     * @return SystemBO
     */
    SystemBO systemDo2Bo(SystemDO systemDo);

    /**
     * List<RoleDO> 转 List<RoleBO>
     *
     * @param roleDoList roleDo List
     * @return roleBo List
     */
    List<RoleBO> roleDoList2BoList(List<RoleDO> roleDoList);

    /**
     * List<ResourceDO> 转 List<ResourceBO>
     *
     * @param resourceDoList resourceDo List
     * @return resourceBo List
     */
    List<ResourceBO> resourceDoList2BoList(List<ResourceDO> resourceDoList);

    /**
     * PermissionDO List 转 PermissionBO List
     *
     * @param permissionDoList permissionDo List
     * @return permissionBo List
     */
    List<PermissionBO> permissionDoList2BoList(List<PermissionDO> permissionDoList);

    /**
     * SystemDO List 转 SystemBO List
     *
     * @param systemDoList SystemDO List
     * @return SystemBO List
     */
    List<SystemBO> systemDoList2BoList(List<SystemDO> systemDoList);
}
