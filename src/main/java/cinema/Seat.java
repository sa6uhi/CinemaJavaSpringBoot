package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean purchased;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = initializePrice();
        this.purchased = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int initializePrice() {
        return row <= 4 ? 10 : 8;
    }
}