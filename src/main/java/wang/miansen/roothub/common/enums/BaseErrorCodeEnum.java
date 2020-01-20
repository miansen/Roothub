package wang.miansen.roothub.common.enums;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public enum BaseErrorCodeEnum {
	
	/**
	 * 无权限访问对应的资源
	 */
	ACCESS_DENIED(403, "AccessDenied", "Access denied"),
	
	/**
	 * 请求中的 JSON 格式正确，但语义上不符合要求。如缺少某个必需项，或值类型不匹配等。
	 * <p>注意：出于兼容性考虑，对于所有无法识别的项应直接忽略，不应该返回这个错误。
	 */
	INAPPROPRIATE_JSON(400, "InappropriateJSON", "The JSON you provided was well-formed and valid, but not appropriate forthis operation"),
	
	/**
	 * 所有未定义的其他错误
	 * <p>注意：在有明确对应的其他类型的错误时（包括通用的和服务自定义的）不应该使用这个错误
	 */
	INTERNAL_ERROR(500, "InternalError", "We encountered an internal error Please try again"),
	
	/**
	 * Access Key ID不存在
	 */
	INVALID_ACCESS_KEY_ID(403, "InvalidAccessKeyId", "The Access Key ID you provided doesnot exist in our records"),
	
	/**
	 * Authorization 头域格式错误
	 */
	INVALID_HTTP_AUTH_HEADER(400, "InvalidHTTPAuthHeader", "The Access Key ID you provided does notexist in our records"),
	
	/**
	 * HTTP body 格式错误。例如不符合指定的 Encoding 等
	 */
	INVALID_HTTP_REQUEST(400, "InvalidHTTPRequest", "There was an error in the body of your HTTP request"),
	
	/**
	 * URI 形式不正确。例如一些服务定义的关键词不匹配等。对于 ID 不匹配的问题，应定义更加具体的错误码，如 NoSuchKey。
	 */
	INVALID_URI(400, "InvalidURI", "Could not parse the specified URI"),
	
	/**
	 * JSON 格式不合法
	 */
	MALFORMED_JSON(400, "MalformedJSON", "The JSON you provided was not well-formed"),
	
	/**
	 * URI 的版本号不合法
	 */
	INVALID_VERSION(404, "InvalidVersion", "The API version specified was invalid"),
	
	/**
	 * 没有开通对应的服务
	 */
	OPT_IN_REQUIRED(403, "OptInRequired", "A subscription for the service is required"),
	
	/**
	 * 指定的 If-Match 标头与 ETag 标头不匹配
	 */
	PRECONDITION_FAILED(412, "PreconditionFailed", "The specified If-Match header doesn’tmatch the ETag header"),
	
	/**
	 * 请求超时
	 */
	REQUEST_EXPIRED(400, "RequestExpired", "Request has expired. Timestamp date is <Data>"),
	
	/**
	 * clientToken 对应的 API 参数不一样
	 */
	IDEMPOTENT_PARAMETER_MISMATCH(403, "IdempotentParameterMismatch", "The request uses the same client token asa previous, but non-identical request"),
	
	/**
	 * Authorization 头域中附带的签名和服务端验证不一致
	 */
	SIGNATURE_DOES_NOT_MATCH(400, "SignatureDoesNotMatch", "The request signature we calculated does not match the signature you provided. Check yourSecret Access Key and signing method. Consultthe service documentation for details");
	
	private int httpCode;
	
	private String errorCode;
	
	private String message;

	private BaseErrorCodeEnum(int httpCode, String errorCode, String message) {
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
