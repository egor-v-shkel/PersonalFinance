package by.javatr.personalfinance.service.exception;

public class UserDataException extends ServiceException {
    public UserDataException() {
        super();
    }

    public UserDataException(String message) {
        super(message);
    }

    public UserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDataException(Throwable cause) {
        super(cause);
    }
}
