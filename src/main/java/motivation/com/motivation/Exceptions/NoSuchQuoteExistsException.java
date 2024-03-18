package motivation.com.motivation.Exceptions;

public class NoSuchQuoteExistsException extends RuntimeException{
    private String message;

    public NoSuchQuoteExistsException() {}

    public NoSuchQuoteExistsException(String message) {
        super(message);
        this.message = message;
    }
}
