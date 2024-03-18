package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserQuoteExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import motivation.com.motivation.Exceptions.UserQuoteAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserQuoteExceptionHandler {
    @ExceptionHandler(value = NoSuchUserQuoteExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchQuotes(NoSuchUserQuoteExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = UserQuoteAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> QuoteAlreadyExists(UserQuoteAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
