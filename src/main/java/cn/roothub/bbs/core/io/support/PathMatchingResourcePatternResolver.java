package cn.roothub.bbs.core.io.support;

import java.io.IOException;

import cn.roothub.bbs.core.io.DefaultResourceLoader;
import cn.roothub.bbs.core.io.Resource;
import cn.roothub.bbs.core.io.ResourceLoader;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

	private final ResourceLoader resourceLoader;
	
	public PathMatchingResourcePatternResolver() {
		this.resourceLoader = new DefaultResourceLoader();
	}

	@Override
	public Resource getResource(String location) {
		return this.resourceLoader.getResource(location);
	}

	@Override
	public Resource[] getResources(String locationPattern) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
