package motivation.com.motivation.Exceptions;

public class NoSuchNotificationInfoExistsException extends RuntimeException{
    private String message;

    public NoSuchNotificationInfoExistsException() {}

    public NoSuchNotificationInfoExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
