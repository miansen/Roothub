package wang.miansen.roothub.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 类路径资源描述，通常是类路径下 class 文件的抽象。
 * @author miansen.wang
 * @date 2019-11-22
 */
public class ClassPathResource extends AbstractResource {

	private final String path;
	
	private ClassLoader classLoader;
	
	private Class<?> clazz;
	
	public ClassPathResource(String path) {
		this.path = path;
	}

	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = classLoader;
	}

	public ClassPathResource(String path, Class<?> clazz) {
		this.path = path;
		this.clazz = clazz;
	}

	@Override
	public boolean exists() {
		return this.resolveURL() != null;
	}

	@Override
	public URL getURL() throws IOException {
		URL url = this.resolveURL();
		if (url == null) {
			throw new FileNotFoundException(this.getDescription() + " cannot be resolved to URL because it does not exist");
		} else {
			return url;
		}
	}

	@Override
	public String getFilename() {
		if (this.path == null) {
			return null;
		} else {
			int separatorIndex = this.path.lastIndexOf("/");
			return separatorIndex != -1 ? path.substring(separatorIndex + 1) : this.path;
		}
	}

	protected URL resolveURL() {
		if (this.clazz != null) {
			return this.clazz.getResource(this.path);
		} else {
			return this.classLoader != null ? this.classLoader.getResource(this.path) : ClassLoader.getSystemResource(this.path);
		}
	}

	@Override
	public String getDescription() {
		StringBuilder builder = new StringBuilder("class path resource [");
		String pathToUse = this.path;
		if (this.clazz != null && !pathToUse.startsWith("/")) {
			String className = clazz.getName();
			String classPackageAsResourcePath;
			int packageEndIndex = className.lastIndexOf(46);
			if (packageEndIndex == -1) {
				classPackageAsResourcePath =  "";
			} else {
				String packageName = className.substring(0, packageEndIndex);
				classPackageAsResourcePath =  packageName.replace('.', '/');
			}
			builder.append(classPackageAsResourcePath);
			builder.append('/');
		}

		if (pathToUse.startsWith("/")) {
			pathToUse = pathToUse.substring(1);
		}

		builder.append(pathToUse);
		builder.append(']');
		return builder.toString();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is;
		if (this.clazz != null) {
			is = this.clazz.getResourceAsStream(this.path);
		}
		else if (this.classLoader != null) {
			is = this.classLoader.getResourceAsStream(this.path);
		}
		else {
			is = ClassLoader.getSystemResourceAsStream(this.path);
		}
		if (is == null) {
			throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
		}
		return is;
	}

}
