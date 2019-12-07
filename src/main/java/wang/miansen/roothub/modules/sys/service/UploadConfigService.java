package wang.miansen.roothub.modules.sys.service;

import java.util.Map;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-02
 */
public interface UploadConfigService extends SystemConfigService {

	Map<String, Object> getUploadConfig();
}
