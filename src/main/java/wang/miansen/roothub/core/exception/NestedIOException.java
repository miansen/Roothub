package wang.miansen.roothub.core.exception;

import java.io.IOException;

/**
 * @author miansen.wang
 * @date 2019-11-22
 */
public class NestedIOException extends IOException {

	public NestedIOException(String msg) {
		super(msg);
	}
	
	public NestedIOException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
