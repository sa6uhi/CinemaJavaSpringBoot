package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

class Ticket {
    @JsonProperty("token")
    private final UUID uuid;
    @JsonProperty("ticket")
    private Seat seat;
    static final ArrayList<Ticket> purchasedTickets = new ArrayList<>();

    public Ticket(Seat seat) {
        this.uuid = UUID.randomUUID();
        this.seat = seat;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Seat getSeat() {
        return seat;
    }

    public static void addTicket(Ticket ticket) {
        purchasedTickets.add(ticket);
    }

    public static void removeTicket(Ticket ticket) {
        purchasedTickets.remove(ticket);
        ticket.seat.setPurchased(false);
    }
}