package wang.miansen.roothub.security.bo;

import java.util.Date;

import lombok.Data;

/**
 * 系统 BO
 *
 * @author miansen.wang
 * @date 2021-06-20 21:20
 */
@Data
public class SecSystemBO {

    /**
     * 主键ID
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
     * 系统URL
     */
    private String systemUrl;

    /**
     * 系统图标
     */
    private String systemIcon;

    /**
     * 系统排序
     */
    private Integer systemSort;

    /**
     * 系统描述
     */
    private String systemDesc;

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
