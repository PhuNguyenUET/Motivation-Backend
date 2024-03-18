package motivation.com.motivation.Exceptions;

public class UserQuoteAlreadyExistsException extends RuntimeException{
    private String message;

    public UserQuoteAlreadyExistsException() {}

    public UserQuoteAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
