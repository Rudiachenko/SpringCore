package spring.core.model.impl;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.model.Event;
import spring.core.model.Identifiable;

import java.util.Date;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class EventImpl implements Event, Identifiable {
    private long id;
    private String title;
    private Date date;
}
