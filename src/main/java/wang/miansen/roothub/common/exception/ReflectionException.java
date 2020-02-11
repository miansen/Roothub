package wang.miansen.roothub.common.exception;

/**
 * 在 {@link ReflectionUtils} 类中遇到错误时可以抛出此异常
 * 
 * @author miansen.wang
 * @date 2020-02-10
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ReflectionException extends RuntimeException {

	public ReflectionException(String msg) {
		super(msg);
	}

	public ReflectionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
