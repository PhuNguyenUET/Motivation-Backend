package motivation.com.motivation.ExceptionHandlers;

import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.QuoteAlreadyExistsException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalQuoteExceptionHandler {
    @ExceptionHandler(value = NoSuchQuoteExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchQuotes(NoSuchQuoteExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = QuoteAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> QuoteAlreadyExists(QuoteAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
