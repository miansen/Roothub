package wang.miansen.roothub.modules.node.enums;

/**
 * @author miansen.wang
 * @date 2020-02-16
 */
public enum NodeErrorCodeEnum {

	ID_MISSING(400, "Node.IdMissing", "ID 为空"),
	
	NAME_MISSING(400, "Node.NameMissing", "名字为空"),
	
	NOT_FOUND(404, "Node.NotFound", "节点不存在"),

	INVALIDATE_NODE(400, "Node.InvalidateNode", "无效的节点");

	private int httpCode;

	private String errorCode;

	private String message;

	private NodeErrorCodeEnum(int httpCode, String errorCode, String message) {
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
