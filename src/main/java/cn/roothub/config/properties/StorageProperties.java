package cn.roothub.config.properties;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.roothub.service.SystemConfigService;

/**
 * <p>本地上传配置</p>
 * @author: miansen.wang
 * @date: 2019-03-19
 */
@Component
public class StorageProperties {

	// 自定义主题文件保存路径
	private String localUploadTopicFiledir;

	// 自定义用户文件保存路径
	private String localUploadUserFiledir;

	// 自定义节点文件保存路径
	private String localUploadNodeFiledir;

	// 自定义标签文件保存路径
	private String localUploadTagFiledir;

	// 静态文件访问URL
	private String staticUrl;
	
	/**
	 * 当upload_type == 30（本地上传）时，初始化变量，保存路径去掉 "file:"，访问URL去掉"**"
	 * 
	 * @param systemConfigService
	 */
	@Autowired
	public StorageProperties(SystemConfigService systemConfigService) {
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		String uploadType = (String) maps.get("upload_type");
		if (uploadType.equals("30")) {
			this.localUploadTopicFiledir = ((String) maps.get("local_upload_topic_filedir")).replace("file:", "");
			this.localUploadUserFiledir = ((String) maps.get("local_upload_user_filedir")).replace("file:", "");
			this.localUploadNodeFiledir = ((String) maps.get("local_upload_node_filedir")).replace("file:", "");
			this.localUploadTagFiledir = ((String) maps.get("local_upload_tag_filedir")).replace("file:", "");
			this.staticUrl = ((String)maps.get("static_url")).replace("**", "");
		}
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

	public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
}
