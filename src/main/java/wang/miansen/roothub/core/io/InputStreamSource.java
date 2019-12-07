package wang.miansen.roothub.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream 资源对象的简单接口
 * @author miansen.wang
 * @date 2019-11-22
 */
public interface InputStreamSource {

	/**
	 * 返回一个 InputStream 作为基础资源的内容。
	 * @return
	 * @throws IOException
	 */
	InputStream getInputStream() throws IOException;
}
