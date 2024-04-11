package mvc.spring.web.services.event.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.mappers.EventMapper;
import mvc.spring.web.models.Club;
import mvc.spring.web.models.Event;
import mvc.spring.web.repositories.ClubRepository;
import mvc.spring.web.repositories.EventRepository;
import mvc.spring.web.services.event.EventService;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event -> EventMapper.mapToEventDto(event)))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto save(long clubId, EventDto eventDto) throws NotFoundException {
        Optional<Club> foundClub = clubRepository.findById(clubId);
        if(foundClub.isPresent()){
            Club club = foundClub.get();
            Event event = EventMapper.mapToEvent(eventDto);
            event.setClub(club);
            eventRepository.save(event);
            return EventMapper.mapToEventDto(event);
        }
        throw new NotFoundException("Club Not Found");
    }

    @Override
    public EventDto findById(long id) throws NotFoundException {
        Optional<Event> foundEvent = eventRepository.findById(id);
        if(foundEvent.isPresent()){
            Event event = eventRepository.findById(id).get();
            return EventMapper.mapToEventDto(event);
        }
        throw new NotFoundException("Event Not Found");
    }

    @Override
    public void update(EventDto event) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<EventDto> search(String query) {
        List<Event> foundEvents = eventRepository.searchEvent(query);
        return foundEvents.stream()
                .map((event) -> EventMapper.mapToEventDto(event))
                .collect(Collectors.toList());
    }

}
