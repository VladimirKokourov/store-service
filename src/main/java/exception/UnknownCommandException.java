package exception;

public class UnknownCommandException extends ApplicationException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
