package cinema.presentation;

public class TicketNotAvailableException extends RuntimeException {

  public TicketNotAvailableException(String message) {
    super(message);
  }
}
