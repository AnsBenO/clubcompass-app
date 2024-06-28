package org.clubcompass.app.services.event.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.clubcompass.app.dto.EventDto;
import org.clubcompass.app.dto.PartialClubDto;
import org.clubcompass.app.mappers.ClubMapper;
import org.clubcompass.app.mappers.EventMapper;
import org.clubcompass.app.models.Club;
import org.clubcompass.app.models.Event;
import org.clubcompass.app.repositories.ClubRepository;
import org.clubcompass.app.repositories.EventRepository;
import org.clubcompass.app.services.event.EventService;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((EventMapper::mapToEventDto))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto save(long clubId, EventDto eventDto) throws NotFoundException {
        Optional<Club> foundClub = clubRepository.findById(clubId);
        if (foundClub.isPresent()) {
            Club club = foundClub.get();
            eventDto.setClub(ClubMapper.mapToPartialClubDto(club));
            Event event = EventMapper.mapToEvent(eventDto);
            eventRepository.save(event);
            return EventMapper.mapToEventDto(event);
        }
        throw new NotFoundException("Club Not Found");
    }

    @Override
    public EventDto findById(long id) throws NotFoundException {
        Optional<Event> foundEvent = eventRepository.findById(id);
        if (foundEvent.isPresent()) {
            Event event = foundEvent.get();
            return EventMapper.mapToEventDto(event);
        }
        throw new NotFoundException("Event Not Found");
    }

    @Override
    public void update(EventDto eventDto) throws NotFoundException {
        long eventId = eventDto.getId();
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            Event updatedEvent = EventMapper.mapToEvent(eventDto);
            updatedEvent.setId(existingEvent.getId());
            eventRepository.save(updatedEvent);
        } else {
            throw new NotFoundException("Event Not Found");
        }
    }

    @Override
    public void deleteById(long id) throws NotFoundException {
        Optional<Event> foundEvent = eventRepository.findById(id);
        if (foundEvent.isPresent()) {
            eventRepository.delete(foundEvent.get());
        } else {
            throw new NotFoundException("Event Not Found");
        }
    }

    @Override
    public List<EventDto> search(String query) {
        List<Event> foundEvents = eventRepository.searchEvent(query);
        return foundEvents.stream()
                .map(EventMapper::mapToEventDto)
                .collect(Collectors.toList());
    }

}
