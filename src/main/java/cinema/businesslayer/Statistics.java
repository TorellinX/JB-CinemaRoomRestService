package cinema.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Statistics {
  @JsonProperty("current_income")
  private int currentIncome;
  @JsonProperty("number_of_available_seats")
  private int numberOfAvailableSeats;
  @JsonProperty("number_of_purchased_tickets")
  private int numberOfPurchasedTickets;

  Statistics(@Qualifier("totalRows") Integer totalRows, @Qualifier("totalColumns") Integer totalColumns){
    this.numberOfAvailableSeats = totalRows * totalColumns;
    this.currentIncome = 0;
    this.numberOfPurchasedTickets = 0;
  }

  public int getCurrentIncome() {
    return currentIncome;
  }

  public void setCurrentIncome(int currentIncome) {
    this.currentIncome = currentIncome;
  }

  public int getNumberOfAvailableSeats() {
    return numberOfAvailableSeats;
  }

  public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
    this.numberOfAvailableSeats = numberOfAvailableSeats;
  }

  public int getNumberOfPurchasedTickets() {
    return numberOfPurchasedTickets;
  }

  public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
    this.numberOfPurchasedTickets = numberOfPurchasedTickets;
  }

  public void purchaseTicket(int price) {
    addIncome(price);
    decreaseNumberOfAvailableSeats();
    increaseNumberOfPurchasedTickets();
  }

  public void returnTicket(int price) {
    subtractIncome(price);
    increaseNumberOfAvailableSeats();
    decreaseNumberOfPurchasedTickets();
  }

  private void increaseNumberOfAvailableSeats() {
    numberOfAvailableSeats++;
  }

  private void decreaseNumberOfAvailableSeats() {
    numberOfAvailableSeats--;
  }

  private void increaseNumberOfPurchasedTickets() {
    numberOfPurchasedTickets++;
  }

  private void decreaseNumberOfPurchasedTickets() {
    numberOfPurchasedTickets--;
  }

  private void addIncome(int income) {
    currentIncome += income;
  }

  private void subtractIncome(int income) {
    currentIncome -= income;
  }




}
