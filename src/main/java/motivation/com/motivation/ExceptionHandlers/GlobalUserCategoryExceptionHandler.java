package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserCategoryExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import motivation.com.motivation.Exceptions.UserCategoryAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserCategoryExceptionHandler {
    @ExceptionHandler(value = NoSuchUserCategoryExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchUserCategory(NoSuchUserCategoryExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = UserCategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> QuoteAlreadyExists(UserCategoryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
