package wang.miansen.roothub.common.beans;

/**
 * @author miansen.wang
 * @date 2020-01-19
 */
public enum ResultStatus {

	/**
	 * 操作成功
	 * <p>用于 {@code GET}、{@code PUT} 和 {@code PATCH} 请求
	 */
	OK(200, "OK"),
	
	/**
	 * 操作成功，已经生成了新的资源
	 * <p>用于 {@code POST} 请求。
	 */
	CREATED(201, "Created"),
	
	/**
	 * 操作成功，资源已经不存在
	 * <p>用于 {@code DELETE} 请求
	 */
	NO_CONTENT(204, "No Content");
	
	private final int value;
	
	private final String reasonPhrase;
	
	private ResultStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int getValue() {
		return value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
}
