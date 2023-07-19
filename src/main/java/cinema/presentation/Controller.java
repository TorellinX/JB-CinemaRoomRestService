/*
  GET statistics
  http://localhost:28852/stats?password=super_secret

  GET available seats
  http://localhost:28852/seats

  POST purchase ticket
  http://localhost:28852/purchase
  {
    "row": 5,
    "column":9
   }

   POST return ticket
   http://localhost:28852/return
   {
    "token": <token>
    }

 */
package cinema.presentation;

import cinema.businesslayer.Room;
import cinema.businesslayer.Seat;
import cinema.businesslayer.Statistics;
import cinema.businesslayer.Ticket;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

  @Autowired
  Room room;

  @GetMapping("/seats")
  public Room getSeats() {
    return room;
  }

  @PostMapping("/purchase")
  public Ticket purchaseTicket(@RequestBody Seat seat) {
    try {
      if (!room.isSeatAvailable(seat)) {
        throw new TicketNotAvailableException("The ticket has been already purchased!");
      }
    } catch (IllegalArgumentException e) {
      throw new SeatOutOfBoundsException(e.getMessage());
    }
    return room.purchaseTicket(seat);

  }

  @PostMapping("/return")
  public Map<String, Seat> returnTicker(@RequestBody Map<String, String> tokenMap) {
    try {
      return Map.of("returned_ticket", room.returnTicket(tokenMap.get("token")));
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @GetMapping("/stats")
  public Statistics showStatistics(
      @RequestParam(value = "password", required = false) String password) {
    try {
      return room.getStatistics(password);
    } catch (IllegalAccessException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }

}
