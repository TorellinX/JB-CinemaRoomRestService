package cinema.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.UUID;

public class Ticket {

  private String token;
  @JsonProperty("ticket")
  private Seat seat;

  public Ticket(Seat seat) {
    this.seat = seat;
    this.token = UUID.randomUUID().toString();
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat ticket) {
    this.seat = ticket;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != getClass()) {
      return false;
    }
    Ticket ticket = (Ticket) o;
    return Objects.equals(this.token, ticket.token) && Objects.equals(this.seat, ticket.seat);
  }

  @Override
  public int hashCode() {
    return 31 * seat.hashCode() + token.hashCode();
  }
}
