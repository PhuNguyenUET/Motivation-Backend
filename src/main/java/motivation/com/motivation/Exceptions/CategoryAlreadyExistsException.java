package motivation.com.motivation.Exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{
    private String message;

    public CategoryAlreadyExistsException() {}

    public CategoryAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
