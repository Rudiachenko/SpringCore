import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.facade.Impl.BookingFacadeImpl;
import spring.core.model.Event;
import spring.core.model.Ticket;
import spring.core.model.User;
import spring.core.model.impl.EventImpl;
import spring.core.model.impl.UserImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void realTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        BookingFacadeImpl bookingFacadeImpl = context.getBean("bookingFacade", BookingFacadeImpl.class);
        User user = createUser("Bob", "bob@gmail.com");
        User createdUser = bookingFacadeImpl.createUser(user);

        assertThat(createdUser.getId(), is(5L));

        Event event = createEvent("NewEvent", new Date(333));

        Event createdEvent = bookingFacadeImpl.createEvent(event);

        assertThat(createdEvent.getId(), is(1L));

        Ticket ticket =
                bookingFacadeImpl.bookTicket(createdUser.getId(), createdEvent.getId(), 5, Ticket.Category.STANDARD);

        assertThat(ticket.getId(), is(1L));
        assertThat(ticket.getUserId(), is(5L));
        assertThat(ticket.getEventId(), is(1L));
        assertThat(ticket.getCategory(), is(Ticket.Category.STANDARD));
        assertThat(ticket.getPlace(), is(5));

        List<Ticket> bookedTickets = bookingFacadeImpl.getBookedTickets(createdUser, 10, 1);

        assertThat(bookedTickets, is(Collections.singletonList(ticket)));

        boolean isCanceled = bookingFacadeImpl.cancelTicket(ticket.getId());
        List<Ticket> bookedTicketsAfterCancellation = bookingFacadeImpl.getBookedTickets(createdUser, 10, 1);

        assertTrue(isCanceled);
        assertThat(bookedTicketsAfterCancellation, is(Collections.emptyList()));

    }

    private UserImpl createUser(String name, String email) {
        return UserImpl.builder()
                .name(name)
                .email(email)
                .build();
    }

    private Event createEvent(String title, Date date) {
        return EventImpl.builder()
                .title(title)
                .date(date)
                .build();
    }

}
