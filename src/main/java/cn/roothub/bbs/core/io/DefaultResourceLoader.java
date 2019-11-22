package cn.roothub.bbs.core.io;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public class DefaultResourceLoader implements ResourceLoader {

	@Override
	public Resource getResource(String location) {
		return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()),
				Thread.currentThread().getContextClassLoader());
	}

}
