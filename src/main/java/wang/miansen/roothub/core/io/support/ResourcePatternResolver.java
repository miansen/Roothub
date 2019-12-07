package wang.miansen.roothub.core.io.support;

import java.io.IOException;

import wang.miansen.roothub.core.io.Resource;
import wang.miansen.roothub.core.io.ResourceLoader;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public interface ResourcePatternResolver extends ResourceLoader {

	String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
	
	Resource[] getResources(String locationPattern) throws IOException;
}
