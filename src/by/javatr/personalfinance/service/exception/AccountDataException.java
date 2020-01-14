package by.javatr.personalfinance.service.exception;

public class AccountDataException extends ServiceException {
    public AccountDataException() {
        super();
    }

    public AccountDataException(String message) {
        super(message);
    }

    public AccountDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDataException(Throwable cause) {
        super(cause);
    }
}
