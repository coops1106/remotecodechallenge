package repository;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException() {
        super();
    }

    public DeviceNotFoundException(final String message) {
        super(message);
    }

    public DeviceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DeviceNotFoundException(final Throwable cause) {
        super(cause);
    }

    public DeviceNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
