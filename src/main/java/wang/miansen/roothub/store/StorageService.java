package wang.miansen.roothub.store;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-20
 */
public interface StorageService {

	/**
	 * 初始化目录
	 * @param path
	 */
	void init(Path path);
	
	/**
	 * 存储文件
	 * @param file 要上传的文件对象
	 * @param path 文件存放路径，路径的最后面没有 "/"
	 * @return 文件访问路径
	 */
	String store(MultipartFile file,Path path);
	
	/**
	 * 存储base64格式的文件
	 * @param base64
	 * @param path
	 * @return
	 */
	String store(String base64, Path path);
	
	Stream<Path> loadAll();
	
	Path load(String filename);
	
	Resource loadAsResource(String filename);
	
	void deleteAll();
}
