package cn.roothub.bbs.core.io.support;

import java.io.IOException;

import cn.roothub.bbs.core.io.Resource;
import cn.roothub.bbs.core.io.ResourceLoader;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public interface ResourcePatternResolver extends ResourceLoader {

	String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
	
	Resource[] getResources(String locationPattern) throws IOException;
}
