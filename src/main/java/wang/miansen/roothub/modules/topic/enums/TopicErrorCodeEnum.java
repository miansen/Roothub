package wang.miansen.roothub.modules.topic.enums;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public enum TopicErrorCodeEnum {

	TITLE_MISSING(400, "Topic.TitleMissing", "标题为空"),

	NODE_ID_MISSING(400, "Topic.NodeIdMissing", "节点 ID 为空"),
	
	USER_ID_MISSING(400, "Topic.NodeIdMissing", "用户 ID 为空"),

	NOT_FOUND(404, "Topic.NotFound", "帖子不存在"),

	CREATE_LIMIT_EXCEEDED(413, "Topic.CreateLimitExceeded", "发帖超过限制"),

	USER_BE_BANNED(403, "Topic.UserBeBanned", "用户已被封禁"),

	INVALIDATE_NODE(400, "Topic.InvalidateNode", "无效的节点"),
	
	INVALIDATE_USER(400, "Topic.InvalidateNode", "无效的用户");

	private int httpCode;

	private String errorCode;

	private String message;

	private TopicErrorCodeEnum(int httpCode, String errorCode, String message) {
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}
