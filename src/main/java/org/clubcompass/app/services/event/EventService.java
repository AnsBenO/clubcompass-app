package org.clubcompass.app.services.event;

import java.util.List;

import org.clubcompass.app.dto.EventDto;
import org.clubcompass.app.services.common.Searchable;

import javassist.NotFoundException;

public interface EventService extends Searchable<EventDto> {
    List<EventDto> findAll();

    EventDto save(long clubId, EventDto event) throws NotFoundException;

    EventDto findById(long id) throws NotFoundException;

    void update(EventDto event) throws NotFoundException;

    void deleteById(long id) throws NotFoundException;

}
