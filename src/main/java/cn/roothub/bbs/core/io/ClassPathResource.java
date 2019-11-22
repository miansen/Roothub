package cn.roothub.bbs.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
	public String getDescription() {
		return null;
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
