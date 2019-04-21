package cn.roothub.store;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.roothub.config.properties.StorageProperties;
import cn.roothub.config.service.OSSService;
import cn.roothub.exception.StorageException;
import cn.roothub.util.FileNameUtil;

/**
 * <p>
 * </p>
 * 
 * @author: miansen.wang
 * @date: 2019-03-20
 */
@Service
public class FileSystemStorageService implements StorageService {

	@Autowired
	private StorageProperties storageProperties;
	
	@Autowired
	private OSSService ossService;
	
	/**
	 * 初始化存储
	 */
	@Override
	public void init(Path path) {
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			throw new StorageException("初始化存储失败", e);
		}
	}

	/**
	 * 存储文件
	 * @param file: 要上传的文件对象
	 * @param path: 文件存放路径
	 * @return 文件访问路径
	 */
	@Override
	public String store(MultipartFile file, Path path) {
		// 文件访问路径
		String fileURL = null;
		// 判断文件是否为空
		if (file.isEmpty()) {
			throw new StorageException("请选择要上传的文件");
		}
		// 判断文件格式是否正确
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			throw new StorageException("文件格式不正确");
		}
		// 初始化上传配置
		storageProperties.init();
		if(storageProperties.getUploadType().equals("45")) {
			fileURL = ossService.uploadFile2OSS(file, path.toString());
		}else {
			// 文件保存目录，数据库里配置的路径 + 自定义路径
			path = Paths.get(storageProperties.getUploadFiledir() + path.toString());
			// 目录不存在则创建
			if (!Files.exists(path)) {
				init(path);
			}
			// 生成新的文件名
			String newFilename = FileNameUtil.getFileName(filename);
			try (InputStream inputStream = file.getInputStream()) {
				// 存储文件
				Files.copy(inputStream, path.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
				fileURL = storageProperties.getStaticUrl() + newFilename;
			} catch (IOException e) {
				throw new StorageException("上传文件异常", e);
			}
		}
		return fileURL;
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
