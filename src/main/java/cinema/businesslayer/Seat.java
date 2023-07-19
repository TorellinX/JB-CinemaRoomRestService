package cinema.businesslayer;

import jakarta.validation.constraints.Min;

public class Seat {

  private int row;
  private int column;
  private int price;

  Seat(int row, int column) {
    this.row = row;
    this.column = column;
    this.price = getPrice();
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getPrice() {
    return row <= 4 ? 10 : 8;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != getClass()) {
      return false;
    }
    Seat seat = (Seat) o;
    return this.row == seat.row && this.column == seat.column;
  }

  @Override
  public int hashCode() {
    int result = getRow();
    result = 31 * result + getColumn();
    return result;
  }
}
