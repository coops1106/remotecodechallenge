package repository.exceptions;

public class NonValidDeviceException extends RuntimeException {

    public NonValidDeviceException() {
        super();
    }

    public NonValidDeviceException(final String message) {
        super(message);
    }

    public NonValidDeviceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NonValidDeviceException(final Throwable cause) {
        super(cause);
    }

    public NonValidDeviceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
