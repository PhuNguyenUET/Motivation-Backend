package motivation.com.motivation.Exceptions;

public class NotificationInfoAlreadyExistsException extends RuntimeException{
    private String message;

    public NotificationInfoAlreadyExistsException() {}

    public NotificationInfoAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
