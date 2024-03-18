package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.CategoryAlreadyExistsException;
import motivation.com.motivation.Exceptions.NoSuchCategoryExistsException;
import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCategoryExceptionHandler {
    @ExceptionHandler(value = NoSuchCategoryExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchCategory(NoSuchCategoryExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> QuoteAlreadyExists(CategoryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
