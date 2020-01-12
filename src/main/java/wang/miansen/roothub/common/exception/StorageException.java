package wang.miansen.roothub.common.exception;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-20
 */
public class StorageException extends RuntimeException{

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
