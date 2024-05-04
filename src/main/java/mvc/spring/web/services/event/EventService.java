package mvc.spring.web.services.event;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.services.common.Searchable;

public interface EventService extends Searchable<EventDto> {
    List<EventDto> findAll();

    EventDto save(long clubId, EventDto event) throws NotFoundException;

    EventDto findById(long id) throws NotFoundException;

    void update(EventDto event) throws NotFoundException;

    void deleteById(long id) throws NotFoundException;

}
