package spring.core.dao.impl;

import lombok.Setter;
import spring.core.dao.EventDao;
import spring.core.model.Event;
import spring.core.storage.impl.EventStorageImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Setter
public class EventDaoImpl implements EventDao {
    private EventStorageImpl eventStorage;

    @Override
    public Event add(Event event) {
        return eventStorage.add(event);
    }

    @Override
    public Optional<Event> getById(long id) {
        return eventStorage.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventStorage.getAll();
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventStorage.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventStorage.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Optional<Event> updateEvent(Event event) {
        return eventStorage.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventStorage.deleteEvent(eventId);
    }

}
