package spring.core.dao.impl;

import lombok.Setter;
import spring.core.dao.TicketDao;
import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;
import spring.core.storage.TicketStorage;

import java.util.List;

@Setter
public class TicketDaoImpl implements TicketDao {
    private TicketStorage ticketStorage;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketStorage.getAllTickets();
    }

    @Override
    public Ticket add(Ticket ticket) {
        return ticketStorage.add(ticket);
    }

    @Override
    public List<Ticket> getBookedTicketsByUserId(User user, int pageSize, int pageNum) {
        return ticketStorage.getBookedTicketsByUserId(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTicketsByEventId(Event event, int pageSize, int pageNum) {
        return ticketStorage.getBookedTicketsByEventId(event, pageSize, pageNum);
    }

    @Override
    public boolean deleteTicket(long ticketId) {
        return ticketStorage.deleteTicket(ticketId);
    }

    @Override
    public boolean isPlaceBooked(long place) {
        return ticketStorage.getAllTickets().stream()
                .anyMatch(ticket -> ticket.getPlace() == place);
    }
}
