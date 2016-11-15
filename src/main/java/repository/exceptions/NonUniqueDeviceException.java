package repository.exceptions;

public class NonUniqueDeviceException extends RuntimeException {

    public NonUniqueDeviceException() {
        super();
    }

    public NonUniqueDeviceException(final String message) {
        super(message);
    }

    public NonUniqueDeviceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NonUniqueDeviceException(final Throwable cause) {
        super(cause);
    }

    public NonUniqueDeviceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
