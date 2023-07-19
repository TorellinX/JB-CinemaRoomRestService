package cinema.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Room {

  @JsonProperty("total_rows")
  private int totalRows;

  @JsonProperty("total_columns")
  private int totalColumns;

  @JsonProperty("available_seats")
  private List<Seat> availableSeats;

  @JsonIgnore
  private Map<String, Seat> purchasedSeats;

  @Autowired
  private Statistics statistics;

  public Room(@Qualifier("totalRows") Integer totalRows,
      @Qualifier("totalColumns") Integer totalColumns) {
    this.totalRows = totalRows;
    this.totalColumns = totalColumns;
    availableSeats = new ArrayList<>();
    for (int row = 1; row <= totalRows; row++) {
      for (int col = 1; col <= totalColumns; col++) {
        availableSeats.add(new Seat(row, col));
      }
    }
    purchasedSeats = new ConcurrentHashMap<>();
  }

  public int getTotalRows() {
    return totalRows;
  }

  public void setTotalRows(int totalRows) {
    this.totalRows = totalRows;
  }

  public int getTotalColumns() {
    return totalColumns;
  }

  public void setTotalColumns(int totalColumns) {
    this.totalColumns = totalColumns;
  }

  public List<Seat> getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(List<Seat> availableSeats) {
    this.availableSeats = availableSeats;
  }

  public boolean isSeatAvailable(Seat seat) throws IllegalArgumentException {
    if (!isSeatInBounds(seat)) {
      throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
    }
    return availableSeats.contains(seat);
  }

  public Ticket purchaseTicket(Seat seat) {
    if (availableSeats.remove(seat)) {
      Ticket ticket = new Ticket(seat);
      purchasedSeats.put(ticket.getToken(), seat);
      statistics.purchaseTicket(seat.getPrice());
      return ticket;
    } else {
      throw new IllegalArgumentException("The ticket has been already purchased!");
    }
  }

  public Seat returnTicket(String token) {
    if (purchasedSeats.containsKey(token)) {
      Seat returnedSeat = purchasedSeats.remove(token);
      availableSeats.add(returnedSeat);
      statistics.returnTicket(returnedSeat.getPrice());
      return returnedSeat;
    }
    throw new IllegalArgumentException("Wrong token!");
  }

  public boolean isSeatInBounds(Seat seat) {
    return seat.getRow() >= 1 && seat.getColumn() >= 1 && seat.getRow() <= totalRows
        && seat.getColumn() <= totalColumns;
  }

  public Statistics getStatistics(String password) throws IllegalAccessException {
    if (!isRightPassword(password)) {
      throw new IllegalAccessException("The password is wrong!");
    }
    return statistics;
  }

  private boolean isRightPassword(String password) {
    return Objects.equals(password, "super_secret");
  }

}
