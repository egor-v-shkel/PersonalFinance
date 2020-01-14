package by.javatr.personalfinance.service.exception;

public class TransactionDataException extends ServiceException {
    public TransactionDataException() {
        super();
    }

    public TransactionDataException(String message) {
        super(message);
    }

    public TransactionDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDataException(Throwable cause) {
        super(cause);
    }
}
