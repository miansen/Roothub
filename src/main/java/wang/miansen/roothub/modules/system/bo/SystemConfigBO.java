package wang.miansen.roothub.modules.system.bo;

import java.util.Date;

import lombok.Data;

/**
 * 系统配置 BO
 *
 * @author miansen.wang
 * @since 2021-04-18 17:01
 */
@Data
public class SystemConfigBO {

    /**
     * 系统配置 ID
     */
    private Long systemConfigId;

    /**
     * 系统配置 key
     */
    private String key;

    /**
     * 系统配置 value
     */
    private String value;

    /**
     * 系统配置描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
