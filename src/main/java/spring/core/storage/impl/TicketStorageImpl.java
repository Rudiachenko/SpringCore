package spring.core.storage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;
import spring.core.storage.TicketStorage;
import spring.core.util.Paginator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TicketStorageImpl implements TicketStorage {
    private final Map<Long, Ticket> ticketStorageMap = new HashMap<>();
    private final Paginator<Ticket> paginator;

    @Autowired
    public TicketStorageImpl(Paginator<Ticket> paginator) {
        this.paginator = paginator;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(ticketStorageMap.values());
    }

    @Override
    public Ticket add(Ticket ticket) {
        return ticketStorageMap.put(ticket.getId(), ticket);
    }

    @Override
    public List<Ticket> getBookedTicketsByUserId(User user, int pageSize, int pageNum) {
        Predicate<Ticket> predicate = ticket -> ticket.getUserId() == user.getId();
        List<Ticket> filteredTickets = getFilteredTickets(predicate);
        return paginator.paginate(filteredTickets, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTicketsByEventId(Event event, int pageSize, int pageNum) {
        Predicate<Ticket> predicate = ticket -> ticket.getEventId() == event.getId();
        List<Ticket> filteredTickets = getFilteredTickets(predicate);
        return paginator.paginate(filteredTickets, pageSize, pageNum);
    }

    @Override
    public boolean deleteTicket(long ticketId) {
        if (ticketStorageMap.containsKey(ticketId)) {
            ticketStorageMap.remove(ticketId);
            return true;
        }
        return false;
    }

    private List<Ticket> getFilteredTickets(Predicate<Ticket> predicate) {
        return ticketStorageMap.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
