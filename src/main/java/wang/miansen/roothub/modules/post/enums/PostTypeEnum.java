package wang.miansen.roothub.modules.post.enums;

import wang.miansen.roothub.common.enums.BaseMasterDataEnum;

/**
 * 帖子类型枚举
 * 
 * @author miansen.wang
 * @date 2020-02-09
 * @since 3.0
 */
public enum PostTypeEnum implements BaseMasterDataEnum {

	TEXT(1000, "文本"),

	IMAGE(1100, "图片"),

	VIDEO(1200, "视频"),

	URL(1300, "链接"),

	OTHER(1400, "其它");

	private Integer code;

	private String desc;

	private PostTypeEnum(Integer code, String desc) {
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

	public PostTypeEnum codeOf(Integer code) {
		for (PostTypeEnum postTypeEnum : values()) {
			if (postTypeEnum.getCode() == code) {
				return postTypeEnum;
			}
		}
		return null;
	}

}
