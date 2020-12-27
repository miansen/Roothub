package wang.miansen.roothub.rbac.bo;

import java.util.Date;

import lombok.Data;

/**
 * 资源 BO
 *
 * @author miansen.wang
 * @date 2020-12-27 15:18:56
 * @since 3.0
 */
@Data
public class ResourceBO {

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源值
     */
    private String resourceValue;

    /**
     * 资源描述
     */
    private String resourceDesc;

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
