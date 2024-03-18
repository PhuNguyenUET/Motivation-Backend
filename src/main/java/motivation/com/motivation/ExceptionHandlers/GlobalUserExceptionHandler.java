package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import motivation.com.motivation.Exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserExceptionHandler {
    @ExceptionHandler(value = NoSuchUserExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchUser(NoSuchUserExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> UserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
