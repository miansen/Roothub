package wang.miansen.roothub.modules.post.enums;

/**
 * 帖子类型枚举
 * 
 * @author miansen.wang
 * @date 2020-02-09
 * @since 3.0
 */
public enum PostTypeEnum {

	TEXT(1000, "文本"),

	IMAGE(1100, "图片"),

	VIDEO(1200, "视频"),

	URL(1300, "链接"),

	OTHER(1400, "其它");

	private Integer code;

	private String value;

	private PostTypeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static PostTypeEnum codeOf(Integer code) {
		for (PostTypeEnum postTypeEnum : values()) {
			if (postTypeEnum.getCode() == code) {
				return postTypeEnum;
			}
		}
		return null;
	}

}
