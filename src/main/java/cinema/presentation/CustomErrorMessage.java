package cinema.presentation;

import java.time.LocalDateTime;

public class CustomErrorMessage {

  private int statusCode;
  private LocalDateTime timestamp;
  private String error;
  private String description;

  public CustomErrorMessage(int statusCode, LocalDateTime timestamp, String message,
      String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.error = message;
    this.description = description;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getError() {
    return error;
  }

  public String getDescription() {
    return description;
  }
}
