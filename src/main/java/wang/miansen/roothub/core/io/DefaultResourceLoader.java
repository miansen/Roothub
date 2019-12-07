package wang.miansen.roothub.core.io;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public class DefaultResourceLoader implements ResourceLoader {

	private ClassLoader classLoader;

	public DefaultResourceLoader() {
		this.classLoader = Thread.currentThread().getContextClassLoader();
	}

	public DefaultResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public Resource getResource(String location) {
		return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), this.getClassLoader());
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public ClassLoader getClassLoader() {
		return this.classLoader != null ? this.classLoader : Thread.currentThread().getContextClassLoader();
	}

}
