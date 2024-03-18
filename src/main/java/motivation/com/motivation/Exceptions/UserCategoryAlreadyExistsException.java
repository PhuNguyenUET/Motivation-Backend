package motivation.com.motivation.Exceptions;

public class UserCategoryAlreadyExistsException extends RuntimeException{
    private String message;

    public UserCategoryAlreadyExistsException() {}

    public UserCategoryAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
