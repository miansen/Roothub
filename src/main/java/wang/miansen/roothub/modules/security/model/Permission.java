package wang.miansen.roothub.modules.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午12:47:26
 */
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 6163933397000219988L;

	private Integer permissionId;
	
	private String permissionName;
	
	private String permissionValue;
	
	private Integer pid;
	
	private Date createDate;
	
	private Date updateDate;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionValue="
				+ permissionValue + ", pid=" + pid + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
