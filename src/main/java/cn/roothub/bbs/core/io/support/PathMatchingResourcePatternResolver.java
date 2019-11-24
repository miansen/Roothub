package cn.roothub.bbs.core.io.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import cn.roothub.bbs.core.io.DefaultResourceLoader;
import cn.roothub.bbs.core.io.Resource;
import cn.roothub.bbs.core.io.ResourceLoader;
import cn.roothub.bbs.core.io.UrlResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

	private static final Logger logger = LoggerFactory.getLogger(PathMatchingResourcePatternResolver.class);

	/**
	 * 资源加载器
	 */
	private final ResourceLoader resourceLoader;

	/**
	 * 空参构造方法，使用默认的资源加载器。
	 */
	public PathMatchingResourcePatternResolver() {
		this.resourceLoader = new DefaultResourceLoader();
	}

	/**
	 * 带參构造方法，可以指定资源加载器。
	 * @param resourceLoader
	 */
	public PathMatchingResourcePatternResolver(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	@Override
	public Resource getResource(String location) {
		return this.getResourceLoader().getResource(location);
	}

	@Override
	public Resource[] getResources(String locationPattern) throws IOException {
		return this.findAllClassPathResources(locationPattern.substring("classpath*:".length()));
	}

	@Override
	public ClassLoader getClassLoader() {
		return this.getResourceLoader().getClassLoader();
	}

	protected Resource[] findAllClassPathResources(String location) throws IOException {
		Set<Resource> result = new LinkedHashSet(16);
		String path = location;
		if (location.startsWith("/")) {
			path = location.substring(1);
		}
		ClassLoader classLoader = this.getClassLoader();
		Enumeration resourceUrls = classLoader != null ? classLoader.getResources(path) : ClassLoader.getSystemResources(path);
		while(resourceUrls.hasMoreElements()) {
			URL url = (URL) resourceUrls.nextElement();
			result.add(new UrlResource(url));
		}
		return result.toArray(new Resource[result.size()]);
	}

	public static class MyLoader extends ClassLoader {
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			try {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
				InputStream is = getClass().getResourceAsStream(fileName);
				if (is == null) {
                    return super.loadClass(name);
                }
				byte[] b = new byte[is.available()];
				is.read(b);
				return defineClass(name, b, 0, b.length);
			} catch (IOException e) {
				throw new ClassNotFoundException(name);
			}
		}
	}
	
}
