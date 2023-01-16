package spring.core.service.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import spring.core.dao.EventDao;
import spring.core.dao.TicketDao;
import spring.core.dao.UserDao;
import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;
import spring.core.model.impl.TicketImpl;
import spring.core.service.TicketService;
import spring.core.util.IdGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Setter
public class TicketServiceImpl implements TicketService {
    private TicketDao ticketDao;
    private EventDao eventDao;
    private UserDao userDao;
    private IdGenerator idGenerator;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        boolean isPlaceBooked = ticketDao.isPlaceBooked(place);
        if (isPlaceBooked) {
            throw new IllegalStateException("This place has already been booked");
        }
        long generatedId = idGenerator.generateId(TicketImpl.class);
        Ticket ticket = createTicket(generatedId, userId, eventId, place, category);
        log.info("Booking ticket with id " + generatedId);
        ticketDao.add(ticket);
        log.info("Ticket was booked successfully");
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        log.info("Getting booked tickets by user");
        List<Ticket> bookedTickets = ticketDao
                .getBookedTicketsByUserId(user, pageSize, pageNum)
                .stream()
                .map(ticket -> Pair.of(eventDao.getById(ticket.getEventId()).get(), ticket))
                .sorted(Comparator.comparing(pair -> pair.getLeft().getDate(), Comparator.reverseOrder()))
                .map(Pair::getRight)
                .collect(Collectors.toList());
        log.info("Tickets were got successfully");

        return bookedTickets;
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        log.info("Getting booked tickets by event");
        List<Ticket> bookedTickets = ticketDao
                .getBookedTicketsByEventId(event, pageSize, pageNum)
                .stream()
                .map(ticket -> ImmutablePair.of(userDao.getUserById(ticket.getUserId()).get(), ticket))
                .sorted(Comparator.comparing(pair -> pair.getLeft().getEmail()))
                .map(Pair::getRight)
                .collect(Collectors.toList());
        log.info("Tickets were got successfully");
        return bookedTickets;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.deleteTicket(ticketId);
    }

    private Ticket createTicket(long generatedId, long userId, long eventId, int place, Ticket.Category category) {
        return new TicketImpl(generatedId, eventId, userId, place, category);
    }
}
