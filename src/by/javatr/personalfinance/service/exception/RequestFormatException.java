package by.javatr.personalfinance.service.exception;

public class RequestFormatException extends ServiceException {

    public RequestFormatException() {
    }

    public RequestFormatException(String message) {
        super(message);
    }

    public RequestFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestFormatException(Throwable cause) {
        super(cause);
    }
}
