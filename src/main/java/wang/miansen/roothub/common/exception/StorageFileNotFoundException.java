package wang.miansen.roothub.common.exception;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-20
 */
public class StorageFileNotFoundException extends RuntimeException {

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
