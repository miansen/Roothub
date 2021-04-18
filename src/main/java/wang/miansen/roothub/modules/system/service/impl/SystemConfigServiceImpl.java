package wang.miansen.roothub.modules.system.service.impl;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.system.bo.SystemConfigBO;
import wang.miansen.roothub.modules.system.dao.SystemConfigDAO;
import wang.miansen.roothub.modules.system.entity.SystemConfigDO;
import wang.miansen.roothub.modules.system.mapper.Bo2DoMapper;
import wang.miansen.roothub.modules.system.mapper.Do2BoMapper;
import wang.miansen.roothub.modules.system.service.SystemConfigService;

/**
 * 系统配置 Service 接口实现类
 *
 * @author miansen.wang
 * @since 2021-04-18 17:07
 */
@Service
@Slf4j
public class SystemConfigServiceImpl implements SystemConfigService, InitializingBean {

    @Autowired
    private SystemConfigDAO systemConfigDao;

    @Autowired
    private Do2BoMapper do2BoMapper;

    @Autowired
    private Bo2DoMapper bo2DoMapper;

    private Properties properties;

    @Override
    public void save(SystemConfigBO systemConfig) {
        if (StringUtils.isEmpty(systemConfig.getKey()) || StringUtils.isEmpty(systemConfig.getValue())) {
            throw new IllegalArgumentException();
        }
        log.info("Save system config. {}", systemConfig);
        systemConfigDao.insert(bo2DoMapper.systemConfigBo2Do(systemConfig));
        clearProperties();
        initProperties();
    }

    @Override
    public void updateById(SystemConfigBO systemConfig) {
        if (systemConfig.getSystemConfigId() == null || StringUtils.isEmpty(systemConfig.getKey()) || StringUtils
            .isEmpty(systemConfig.getValue())) {
            throw new IllegalArgumentException();
        }
        log.info("Update system config. {}", systemConfig);
        systemConfigDao.updateById(bo2DoMapper.systemConfigBo2Do(systemConfig));
        clearProperties();
        initProperties();
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    private void initProperties() {
        List<SystemConfigDO> systemConfigs = systemConfigDao.selectList(null);
        systemConfigs.forEach(v -> properties.setProperty(v.getKey(), v.getValue()));
    }

    private void clearProperties() {
        properties = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initProperties();
    }
}
