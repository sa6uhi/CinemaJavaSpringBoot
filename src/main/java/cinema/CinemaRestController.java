package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class CinemaRestController {
    CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getAvailableSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeats(@RequestBody Seat seat) {
        if (seat.getRow() > cinemaRoom.getRows() || seat.getColumn() > cinemaRoom.getColumns()) {
            throw new OutOfBoundsException();
        }

        Seat requestedSeat = cinemaRoom.getSeats().stream()
                .filter(s -> s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn())
                .findFirst()
                .orElseThrow(OutOfBoundsException::new);

        if (requestedSeat.isPurchased()) {
            throw new TicketAlreadyPurchasedException();
        }

        requestedSeat.setPurchased(true);
        Ticket ticket = new Ticket(requestedSeat);
        Ticket.addTicket(ticket);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTickets(@RequestBody Map<String, String> requestBody) {
        String uuid = requestBody.get("token");

        for (Ticket ticket : Ticket.purchasedTickets) {
            if (Objects.equals(uuid, ticket.getUuid().toString())) {
                Ticket.removeTicket(ticket);
                return ResponseEntity.ok(Map.of("ticket", ticket.getSeat()));
            }
        }
        throw new ExpiredTokenException();
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(required = false)String password) {
        if (Objects.isNull(password)) {
            throw new WrongPasswordException();
        } else {
            return ResponseEntity.ok(new Statistics(cinemaRoom));
        }
    }

}