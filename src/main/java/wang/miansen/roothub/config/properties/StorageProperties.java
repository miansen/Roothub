package wang.miansen.roothub.config.properties;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import wang.miansen.roothub.modules.sys.service.SystemConfigService;

/**
 * <p>
 * 存储配置
 * </p>
 * 
 * @author: miansen.wang
 * @date: 2019-03-19
 */
@Component
@Scope("prototype")
public class StorageProperties implements BaseProperties{

	// 文件上传的保存路径
	private String uploadFiledir;
	
	// AccessKeyId
	private String accessKeyId;

	// AccessKeySecret
	private String accessKeySecret;

	// Endpoint
	private String endpoint;

	// BucketName
	private String bucketName;

	// 阿里云OSS静态文件存储路径
	private String ossFiledir;

	// 静态文件访问URL
	private String staticUrl;
	
	// 上传类型
	private String uploadType;
	
	static {
		System.out.println("StorageProperties初始化。。。");
	}
	
	@Autowired
	@Qualifier("uploadConfig")
	private SystemConfigService systemConfigService;
	
	@Autowired(required = false)
	private HttpServletRequest httpServletRequest;
	
	@Override
	public void init() {
		// 获取程序的部署路径
		String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/");
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		/**
		 * 获取上传类型
		 * 29: 默认上传
		 * 30: 自定义上传
		 * 45: 阿里云OSS上传
		 */
		String uploadType = (String) maps.get("upload_type");
		if (uploadType.equals("29")) {
			this.uploadFiledir = realPath + (String) maps.get("default_upload_filedir");
			this.staticUrl = ((String) maps.get("static_url")).replace("**", "");
			this.uploadType = "29";
		}
		if (uploadType.equals("30")) {
			this.uploadFiledir = ((String) maps.get("local_upload_filedir")).replace("file:", "");
			this.staticUrl = ((String) maps.get("static_url")).replace("**", "");
			this.uploadType = "30";
		}
		if (uploadType.equals("45")) {
			this.accessKeyId = ((String) maps.get("accessKeyId"));
			this.accessKeySecret = ((String) maps.get("accessKeySecret"));
			this.endpoint = ((String) maps.get("endpoint"));
			this.bucketName = ((String) maps.get("bucketName"));
			this.ossFiledir = ((String) maps.get("oss_filedir"));
			this.staticUrl = ((String) maps.get("oss_static_url"));
			this.uploadType = "45";
		}
	}

	public String getUploadFiledir() {
		return uploadFiledir;
	}

	public void setUploadFiledir(String uploadFiledir) {
		this.uploadFiledir = uploadFiledir;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getOssFiledir() {
		return ossFiledir;
	}

	public void setOssFiledir(String ossFiledir) {
		this.ossFiledir = ossFiledir;
	}

	public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	@Override
	public String toString() {
		return "StorageProperties [uploadFiledir=" + uploadFiledir + ", accessKeyId=" + accessKeyId
				+ ", accessKeySecret=" + accessKeySecret + ", endpoint=" + endpoint + ", bucketName=" + bucketName
				+ ", ossFiledir=" + ossFiledir + ", staticUrl=" + staticUrl + ", uploadType=" + uploadType
				+ ", systemConfigService=" + systemConfigService + ", httpServletRequest=" + httpServletRequest + "]";
	}

}
