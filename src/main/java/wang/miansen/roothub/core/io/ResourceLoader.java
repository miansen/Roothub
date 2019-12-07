package wang.miansen.roothub.core.io;

/**
 * 资源加载器
 * @author miansen.wang
 * @date 2019-11-22
 */
public interface ResourceLoader {

	String CLASSPATH_URL_PREFIX = "classpath:";
	
	/**
	 * 返回资源的引用
	 * <ul>
	 * <li>支持完全限定的 URL，例如："file:C:/test.txt"
	 * <li>支持类路径的 URL，例如："classpath:test.class"
	 * </ul>
	 * <p>注意：资源引用并不代表资源一定存在，需要调用 Resource.exists() 来检查是否存在。
	 * @param location 资源位置
	 * @return
	 */
	Resource getResource(String location);

	ClassLoader getClassLoader();
}
