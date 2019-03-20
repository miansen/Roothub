package cn.roothub.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import cn.roothub.exception.StorageException;
import cn.roothub.service.StorageService;
import cn.roothub.util.FileNameUtil;

/**
 * <p>
 * </p>
 * 
 * @author: miansen.wang
 * @date: 2019-03-20
 */
@Service
public class StorageServiceImpl implements StorageService {

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
	 */
	@Override
	public String store(MultipartFile file, Path path) {
		// 获取上传的文件名
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (file.isEmpty()) {
			throw new StorageException("请选择要上传的文件");
		}
		if (filename.contains("..")) {
			throw new StorageException("文件格式不正确");
		}
		if (!Files.exists(path)) {
			init(path);
		}
		// 生成新的文件名
		String newFilename = FileNameUtil.getFileName(filename);
		try (InputStream inputStream = file.getInputStream()) {
			// 存储文件
			Files.copy(inputStream, path.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
			return newFilename;
		} catch (IOException e) {
			throw new StorageException("上传文件异常", e);
		}

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
