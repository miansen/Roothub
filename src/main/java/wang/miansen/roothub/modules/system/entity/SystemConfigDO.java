package wang.miansen.roothub.modules.system.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 系统配置实体类
 *
 * @author miansen.wang
 * @since 2021-04-18 16:57
 */
@Data
@Table(value = "system_config")
public class SystemConfigDO {

    /**
     * 系统配置 ID
     */
    @Id(value = "system_config_id", type = IdType.AUTO)
    private Long systemConfigId;

    /**
     * 系统配置 key
     */
    private String systemConfigKey;

    /**
     * 系统配置 value
     */
    private String systemConfigValue;

    /**
     * 系统配置描述
     */
    private String systemConfigDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
