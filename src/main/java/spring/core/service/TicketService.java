package spring.core.service;

import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
