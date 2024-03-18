package motivation.com.motivation.Exceptions;

public class NoSuchUserCategoryExistsException extends RuntimeException{
    private String message;

    public NoSuchUserCategoryExistsException() {}

    public NoSuchUserCategoryExistsException(String message) {
        super(message);
        this.message = message;
    }
}
