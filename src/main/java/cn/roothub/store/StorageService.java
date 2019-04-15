package cn.roothub.store;

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
	 * @param file
	 * @param path
	 */
	String store(MultipartFile file,Path path);
	
	Stream<Path> loadAll();
	
	Path load(String filename);
	
	Resource loadAsResource(String filename);
	
	void deleteAll();
}
