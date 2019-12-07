package wang.miansen.roothub.modules.sys.model;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public class SystemConfig {

	private Integer systemConfigId;
	private String key;
	private String value;
	private String description;
	private Integer pid;
	private String type;
	private String option;
	private Boolean reboot;
	private Boolean isDelete;
	
	public Integer getSystemConfigId() {
		return systemConfigId;
	}
	public void setSystemConfigId(Integer systemConfigId) {
		this.systemConfigId = systemConfigId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Boolean getReboot() {
		return reboot;
	}
	public void setReboot(Boolean reboot) {
		this.reboot = reboot;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "SystemConfig [systemConfigId=" + systemConfigId + ", key=" + key + ", value=" + value + ", description="
				+ description + ", pid=" + pid + ", type=" + type + ", option=" + option + ", reboot=" + reboot
				+ ", isDelete=" + isDelete + "]";
	}
	
}
