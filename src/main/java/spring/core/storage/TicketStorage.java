package spring.core.storage;

import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;

import java.util.List;

public interface TicketStorage {
    List<Ticket> getAllTickets();

    Ticket add(Ticket ticket);

    List<Ticket> getBookedTicketsByUserId(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTicketsByEventId(Event event, int pageSize, int pageNum);

    boolean deleteTicket(long ticketId);
}
