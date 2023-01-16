package spring.core.storage;

import spring.core.model.Event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventStorage {
    Event add(Event event);

    Optional<Event> getById(long id);

    List<Event> getAll();

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    Optional<Event> updateEvent(Event event);

    boolean deleteEvent(long eventId);
}
