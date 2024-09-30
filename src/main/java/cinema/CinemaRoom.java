package cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {

    private final int rows;
    private final int columns;
    private final List<Seat> seats = new ArrayList<>();

    public CinemaRoom(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        generateSeats();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    private void generateSeats() {
        if (seats.isEmpty()) {
            for (int i = 1; i <= getRows(); i++) {
                for (int j = 1; j <= getColumns(); j++) {
                    seats.add(new Seat(i, j));
                }
            }
        }
    }
}
