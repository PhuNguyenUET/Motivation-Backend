package motivation.com.motivation.Exceptions;

public class QuoteAlreadyExistsException extends RuntimeException{
    private String message;

    public QuoteAlreadyExistsException() {}

    public QuoteAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
