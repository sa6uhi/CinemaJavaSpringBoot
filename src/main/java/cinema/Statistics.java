package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Statistics {
    private final int income;
    @JsonProperty("available")
    private final int availableSeats;
    @JsonProperty("purchased")
    private final int purchasedSeats;

    public Statistics(CinemaRoom cinemaRoom) {
        this.income = calculateIncome();
        this.purchasedSeats = Ticket.purchasedTickets.size();
        this.availableSeats = cinemaRoom.getSeats().size() - this.purchasedSeats;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getPurchasedSeats() {
        return purchasedSeats;
    }

    private int calculateIncome() {
        List<Ticket> tickets = Ticket.purchasedTickets;
        int totalIncome = 0;
        for (Ticket ticket : tickets) {
            totalIncome += ticket.getSeat().getPrice();
        }
        return totalIncome;
    }
}
