package wang.miansen.roothub.third.service;

import java.io.IOException;
import java.io.InputStream;

import wang.miansen.roothub.common.exception.StorageException;
import wang.miansen.roothub.common.util.FileNameUtil;
import wang.miansen.roothub.config.properties.StorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-13
 */
@Component
public class OSSService implements BaseService<OSSClient>{

	private Logger log = LoggerFactory.getLogger(OSSService.class);
	
	@Autowired
	private StorageProperties storageProperties;
	
	private OSSClient ossClient;
	
	/**
	 * 初始化OSSClient
	 */
	@Override
	public OSSClient instance() {
		storageProperties.init();
		if(ossClient != null) {
			return ossClient;
		}
		ossClient = new OSSClient(storageProperties.getEndpoint(), storageProperties.getAccessKeyId(),storageProperties.getAccessKeySecret());
		return ossClient;
	}

	/**
	 * 上传到OSS服务器 ，如果同名文件会覆盖
	 * @param file 要上传的文件对象
	 * @param customPath 自定义存放路径，格式类似 avatar/admin，最后一个路径没有 "/"
	 * @return 文件访问路径，格式如: 阿里云静态资源访问URL + 数据库里配置的路径 + 自定义路径 + 文件名
	 */
	public String uploadFile2OSS(MultipartFile file,String customPath) {
		// 判断文件是否为空
		if (file.isEmpty()) {
			throw new StorageException("请选择要上传的文件");
		}
		// 判断文件格式是否正确
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			throw new StorageException("文件格式不正确");
		}
		// 生成新的文件名
		String newFilename = FileNameUtil.getFileName(filename);
		try {
			// 上传文件
			uploadFile2OSS(file.getInputStream(),customPath + "/" + newFilename);
		} catch (IOException e) {
			log.error("图片上传OSS失败",e);
		}
		return storageProperties.getStaticUrl() + storageProperties.getOssFiledir() + customPath + "/" + newFilename;
	}
	
	/**
	 * 上传到OSS服务器 ，如果同名文件会覆盖
	 * @param instream 文件流
	 * @param path 自定义的路径+文件名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String path) {
		String ret = "";
        try {
        	// 创建上传Object的Metadata
        	ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
	        objectMetadata.setHeader("Pragma", "no-cache");
	        objectMetadata.setContentType(getcontentType(path.substring(path.lastIndexOf("."))));
	        objectMetadata.setContentDisposition("inline;filename=" + path);
	        // 上传文件
	        PutObjectResult putObject = instance().putObject(storageProperties.getBucketName(),storageProperties.getOssFiledir() + path,instream,objectMetadata);
	        ret = putObject.getETag();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}finally {
			try {
				if(instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
        return ret;
	}
	
	/**
	 * 判断OSS服务文件上传时文件的contentType
	 * @param FilenameExtension 文件后缀
	 * @return String 文件类型
	 */
	public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}
