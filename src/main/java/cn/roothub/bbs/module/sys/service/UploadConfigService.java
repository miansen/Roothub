package cn.roothub.bbs.module.sys.service;

import cn.roothub.bbs.module.sys.service.SystemConfigService;

import java.util.Map;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-02
 */
public interface UploadConfigService extends SystemConfigService {

	Map<String, Object> getUploadConfig();
}
