package wang.miansen.roothub.modules.system.service;

import java.util.Properties;

import wang.miansen.roothub.modules.system.bo.SystemConfigBO;

/**
 * 系统配置 Service 接口
 *
 * @author miasnen.wang
 * @since 2021-04-18 17:06
 */
public interface SystemConfigService {

    /**
     * 新增系统配置
     *
     * @param systemConfig 系统配置
     */
    void save(SystemConfigBO systemConfig);

    /**
     * 更新系统配置
     *
     * @param systemConfig 系统配置
     */
    void updateById(SystemConfigBO systemConfig);

    /**
     * 获取所有的系统配置（键值对的形式）
     *
     * @return 所有的系统配置（键值对的形式）
     */
    Properties getProperties();

    /**
     * 初始化所有的系统配置（键值对的形式）
     */
    void initProperties();

    /**
     * 清除所有的系统配置（键值对的形式）
     */
    void clearProperties();

    /**
     * 根据 key 获取 value
     *
     * @param key key
     * @return value
     */
    String getValue(String key);
}
