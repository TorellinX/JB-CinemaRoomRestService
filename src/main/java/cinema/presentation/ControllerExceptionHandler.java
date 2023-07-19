package cinema.presentation;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SeatOutOfBoundsException.class)
  public ResponseEntity<CustomErrorMessage> handleSeatOutOfBounds(SeatOutOfBoundsException e,
      WebRequest request) {
    CustomErrorMessage body = new CustomErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        e.getMessage(),
        request.getDescription(false)
    );
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TicketNotAvailableException.class)
  public ResponseEntity<CustomErrorMessage> handleTicketNotAvailable(TicketNotAvailableException e,
      WebRequest request) {
    CustomErrorMessage body = new CustomErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        e.getMessage(),
        request.getDescription(false)
    );
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ExceptionHandler(ResponseStatusException.class)
  protected ResponseEntity<Object> handleControllerException(ResponseStatusException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", ex.getStatusCode().value());
    body.put("timestamp", LocalDateTime.now());
    body.put("error", ex.getReason());
    return new ResponseEntity<>(body, ex.getStatusCode());
  }

}
