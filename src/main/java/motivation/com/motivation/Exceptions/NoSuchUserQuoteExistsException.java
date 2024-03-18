package motivation.com.motivation.Exceptions;

public class NoSuchUserQuoteExistsException extends RuntimeException{
    private String message;

    public NoSuchUserQuoteExistsException() {}

    public NoSuchUserQuoteExistsException(String message) {
        super(message);
        this.message = message;
    }
}
