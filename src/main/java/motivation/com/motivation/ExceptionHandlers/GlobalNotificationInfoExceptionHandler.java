package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.NoSuchNotificationInfoExistsException;
import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.NotificationInfoAlreadyExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalNotificationInfoExceptionHandler {
    @ExceptionHandler(value = NoSuchNotificationInfoExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchNotificationInfo(NoSuchNotificationInfoExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = NotificationInfoAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> notificationInfoAlreadyExists(NotificationInfoAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
