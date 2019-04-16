package cn.roothub.config.properties;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cn.roothub.service.SystemConfigService;

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

	// 默认话题文件保存路径
	private String defaultUploadTopicFiledir;

	// 默认节点文件保存路径
	private String defaultUploadNodeFiledir;

	// 默认的标签文件保存路径
	private String defaultUploadTagFiledir;

	// 默认的用户文件保存路径
	private String defaultUploadUserFiledir;

	// 自定义主题文件保存路径
	private String localUploadTopicFiledir;

	// 自定义用户文件保存路径
	private String localUploadUserFiledir;

	// 自定义节点文件保存路径
	private String localUploadNodeFiledir;

	// 自定义标签文件保存路径
	private String localUploadTagFiledir;

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

	private Integer age;
	
	/**
	 * 上传类型
	 * 1000: 默认上传 
	 * 1100: 自定义上传 
	 * 1200: 阿里云OSS上传
	 */
	private String uploadType;
	
	static {
		System.out.println("StorageProperties初始化。。。");
	}
	
	@Autowired
	@Qualifier("uploadConfig")
	private SystemConfigService systemConfigService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Override
	public void init() {
		String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/");
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		String uploadType = (String) maps.get("upload_type");
		this.age = systemConfigService.getAge();
		//默认上传
		if (uploadType.equals("29")) {
			this.defaultUploadTopicFiledir = realPath + (String) maps.get("default_upload_topic_filedir");
			this.defaultUploadUserFiledir = realPath + (String) maps.get("default_upload_user_filedir");
			this.defaultUploadNodeFiledir = realPath + (String) maps.get("default_upload_node_filedir");
			this.defaultUploadTagFiledir = realPath + (String) maps.get("default_upload_tag_filedir");
			this.staticUrl = ((String) maps.get("static_url")).replace("**", "");
			this.uploadType = "1000";
		}

		// 自定义上传
		if (uploadType.equals("30")) {
			this.localUploadTopicFiledir = ((String) maps.get("local_upload_topic_filedir")).replace("file:", "");
			this.localUploadUserFiledir = ((String) maps.get("local_upload_user_filedir")).replace("file:", "");
			this.localUploadNodeFiledir = ((String) maps.get("local_upload_node_filedir")).replace("file:", "");
			this.localUploadTagFiledir = ((String) maps.get("local_upload_tag_filedir")).replace("file:", "");
			this.staticUrl = ((String) maps.get("static_url")).replace("**", "");
			this.uploadType = "1100";
		}

		// 阿里云OSS上传
		if (uploadType.equals("45")) {
			this.accessKeyId = ((String) maps.get("accessKeyId"));
			this.accessKeySecret = ((String) maps.get("accessKeySecret"));
			this.endpoint = ((String) maps.get("endpoint"));
			this.bucketName = ((String) maps.get("bucketName"));
			this.ossFiledir = ((String) maps.get("oss_filedir"));
			this.staticUrl = ((String) maps.get("oss_static_url"));
			this.uploadType = "1200";
		}
	}

	
	public String getDefaultUploadTopicFiledir() {
		return defaultUploadTopicFiledir;
	}

	public void setDefaultUploadTopicFiledir(String defaultUploadTopicFiledir) {
		this.defaultUploadTopicFiledir = defaultUploadTopicFiledir;
	}

	public String getDefaultUploadNodeFiledir() {
		return defaultUploadNodeFiledir;
	}

	public void setDefaultUploadNodeFiledir(String defaultUploadNodeFiledir) {
		this.defaultUploadNodeFiledir = defaultUploadNodeFiledir;
	}

	public String getDefaultUploadTagFiledir() {
		return defaultUploadTagFiledir;
	}

	public void setDefaultUploadTagFiledir(String defaultUploadTagFiledir) {
		this.defaultUploadTagFiledir = defaultUploadTagFiledir;
	}

	public String getDefaultUploadUserFiledir() {
		return defaultUploadUserFiledir;
	}

	public void setDefaultUploadUserFiledir(String defaultUploadUserFiledir) {
		this.defaultUploadUserFiledir = defaultUploadUserFiledir;
	}

	public String getLocalUploadTopicFiledir() {
		return localUploadTopicFiledir;
	}

	public void setLocalUploadTopicFiledir(String localUploadTopicFiledir) {
		this.localUploadTopicFiledir = localUploadTopicFiledir;
	}

	public String getLocalUploadUserFiledir() {
		return localUploadUserFiledir;
	}

	public void setLocalUploadUserFiledir(String localUploadUserFiledir) {
		this.localUploadUserFiledir = localUploadUserFiledir;
	}

	public String getLocalUploadNodeFiledir() {
		return localUploadNodeFiledir;
	}

	public void setLocalUploadNodeFiledir(String localUploadNodeFiledir) {
		this.localUploadNodeFiledir = localUploadNodeFiledir;
	}

	public String getLocalUploadTagFiledir() {
		return localUploadTagFiledir;
	}

	public void setLocalUploadTagFiledir(String localUploadTagFiledir) {
		this.localUploadTagFiledir = localUploadTagFiledir;
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

	
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StorageProperties [defaultUploadTopicFiledir=" + defaultUploadTopicFiledir
				+ ", defaultUploadNodeFiledir=" + defaultUploadNodeFiledir + ", defaultUploadTagFiledir="
				+ defaultUploadTagFiledir + ", defaultUploadUserFiledir=" + defaultUploadUserFiledir
				+ ", localUploadTopicFiledir=" + localUploadTopicFiledir + ", localUploadUserFiledir="
				+ localUploadUserFiledir + ", localUploadNodeFiledir=" + localUploadNodeFiledir
				+ ", localUploadTagFiledir=" + localUploadTagFiledir + ", accessKeyId=" + accessKeyId
				+ ", accessKeySecret=" + accessKeySecret + ", endpoint=" + endpoint + ", bucketName=" + bucketName
				+ ", ossFiledir=" + ossFiledir + ", staticUrl=" + staticUrl + ", uploadType=" + uploadType + "]";
	}

}
