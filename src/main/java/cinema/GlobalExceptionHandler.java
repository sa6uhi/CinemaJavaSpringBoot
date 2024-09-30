package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<Map<String, String>> handleTicketAlreadyPurchased() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "The ticket has been already purchased!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handleOutOfBounds() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Map<String, String>> handleExpiredToken() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Wrong token!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> handleWrongPassword() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "The password is wrong!");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}

class TicketAlreadyPurchasedException extends RuntimeException {
    public TicketAlreadyPurchasedException() {
        super("alreadyPurchasedSeat");
    }
}

class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException() {
        super("indexOutOfBounds");
    }
}

class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("expiredOrWrongTicketToken");
    }
}

class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("wrongPassword");
    }
}