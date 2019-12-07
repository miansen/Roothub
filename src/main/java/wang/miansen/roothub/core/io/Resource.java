package wang.miansen.roothub.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * 资源描述符的接口，它从基础资源的实际类型中抽象出来，例如文件或类路径资源。
 * @author miansen.wang
 * @date 2019-11-22
 */
public interface Resource extends InputStreamSource {

	/**
	 * 确定此资源是否实际以物理形式存在。
	 * @return
	 */
	boolean exists();
	
	/**
	 * 指示是否可以通过 getInputStream() 读取此资源的非空内容。
	 * <p>对于存在的资源描述符将返回 true，返回 false 表示无法读取资源内容。
	 * <p>返回 true 时，尝试进行实际的内容读取仍可能会失败。
	 * @return
	 */
	default boolean isReadable() {
		return exists();
	}

	/**
	 * 指示此资源是否代表具有开放流的句柄。 如果为true，则不能多次读取 InputStream，必须对其进行读取和关闭，以避免资源泄漏。
	 * @return
	 */
	default boolean isOpen() {
		return false;
	}
	
	/**
	 * 确定此资源是否代表文件系统中的文件。 值为 true 表示可以调用 getFile()，但不能保证一定成功。
	 * @return
	 */
	default boolean isFile(){
		return false;
	}
	
	/**
	 * 将此资源解析成 URL 并返回引用。
	 * @return
	 * @throws IOException
	 */
	URL getURL() throws IOException;
	
	/**
	 * 将此资源解析成 URI 并返回引用。
	 * @return
	 * @throws IOException
	 */
	URI getURI() throws IOException;
	
	/**
	 * 将此资源解析成 File 并返回引用。
	 * @return
	 * @throws IOException
	 */
	File getFile() throws IOException;
	
	/**
	 * 返回此资源的内容长度
	 * @return
	 * @throws IOException
	 */
	long contentLength() throws IOException;
	
	/**
	 * 返回此资源的最后修改的时间戳。
	 * @return
	 * @throws IOException
	 */
	long lastModified() throws IOException;
	
	/**
	 * 返回此资源的文件名，即通常路径的最后一部分：例如 "myfile.txt"。
	 * <p>如果此类型的资源没有文件名，则返回null。
	 * @return
	 */
	String getFilename();
	
	/**
	 * 返回对此资源的描述。
	 * <p>也可以从 toString() 方法返回此值。
	 * @return
	 */
	String getDescription();
}
