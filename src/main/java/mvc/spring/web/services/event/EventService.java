package mvc.spring.web.services.event;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.services.common.Searchable;

public interface EventService extends Searchable<EventDto> {
    List<EventDto> findAll();

    EventDto save(Long clubId, EventDto event);

    EventDto findById(Long id) throws NotFoundException;

    void update(EventDto event) throws NotFoundException;

    void deleteById(Long id);

}
