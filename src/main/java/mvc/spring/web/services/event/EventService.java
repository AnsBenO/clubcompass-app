package mvc.spring.web.services.event;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.EventDto;

public interface EventService {
    List<EventDto> findAll();

    EventDto save(Long clubId, EventDto event);

    EventDto findById(Long id) throws NotFoundException;

    void update(EventDto event) throws NotFoundException;

    void deleteById(Long id);
}
