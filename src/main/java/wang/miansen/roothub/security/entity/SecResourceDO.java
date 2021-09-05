package wang.miansen.roothub.security.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 资源实体类
 *
 * @author miansen.wang
 * @date 2020-12-22 21:15:54
 * @since 3.0
 */
@Data
@Table(value = "sec_resource")
public class SecResourceDO {

    /**
     * 资源ID
     */
    @Id(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    /**
     * 系统ID
     */
    private Long systemId;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块编码
     */
    private String moduleCode;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 资源描述
     */
    private String resourceDesc;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 更新人ID
     */
    private Long updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserName;

    /**
     * 是否禁用（0：启用，1：禁用）
     */
    private Boolean isDisabled;

    /**
     * 是否删除（0：有效，1：删除）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
