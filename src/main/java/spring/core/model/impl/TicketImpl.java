package spring.core.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.model.Identifiable;
import spring.core.model.Ticket;

@Builder
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class TicketImpl implements Ticket, Identifiable {
    private long id;
    private long eventId;
    private long userId;
    private int place;
    private Category category;
}
