package wang.miansen.roothub.rbac.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import wang.miansen.roothub.rbac.bo.PermissionBO;
import wang.miansen.roothub.rbac.bo.ResourceBO;
import wang.miansen.roothub.rbac.bo.RoleBO;
import wang.miansen.roothub.rbac.entity.PermissionDO;
import wang.miansen.roothub.rbac.entity.ResourceDO;
import wang.miansen.roothub.rbac.entity.RoleDO;

/**
 * DO 转 BO
 *
 * @author miansen.wang
 * @date 2020-12-27 15:21
 */
@Mapper(componentModel = "spring")
public interface Do2BoMapper {

    /**
     * RoleDO 转 RoleBO
     * @param roleDO
     * @return
     */
    RoleBO roleDo2Bo(RoleDO roleDO);

    /**
     * PermissionDO 转 PermissionBO
     *
     * @param permissionDO
     * @return
     */
    PermissionBO permissionDo2Bo(PermissionDO permissionDO);

    /**
     * ResourceDO 转 ResourceBO
     *
     * @param resourceDO
     * @return
     */
    ResourceBO resourceDo2Bo(ResourceDO resourceDO);

    /**
     * List<RoleDO> 转 List<RoleBO>
     *
     * @param roleDOList
     * @return
     */
    List<RoleBO> roleDoList2BoList(List<RoleDO> roleDOList);

    /**
     * List<ResourceDO> 转 List<ResourceBO>
     *
     * @param resourceDOList
     * @return
     */
    List<ResourceBO> resourceDoList2BoList(List<ResourceDO> resourceDOList);

    /**
     * List<PermissionDO> 转 List<PermissionBO>
     *
     * @param permissionDOList
     * @return
     */
    List<PermissionBO> permissionDoList2BoList(List<PermissionDO> permissionDOList);
}
