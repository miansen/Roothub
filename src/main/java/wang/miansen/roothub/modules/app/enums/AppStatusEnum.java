package wang.miansen.roothub.modules.app.enums;

import wang.miansen.roothub.common.enums.BaseMasterDataEnum;

/**
 * 应用状态枚举
 * 
 * @author miansen.wang
 * @date 2020-02-09
 * @since 3.0
 */
public enum AppStatusEnum implements BaseMasterDataEnum {

	DRAFT(1000, "草稿"),

	PUBLISH(1100, "发布"),

	STOP(1200, "停用");

	private Integer code;

	private String desc;

	private AppStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public AppStatusEnum codeOf(Integer code) {
		for (AppStatusEnum postTypeEnum : values()) {
			if (postTypeEnum.getCode() == code) {
				return postTypeEnum;
			}
		}
		return null;
	}

}
