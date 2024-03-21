package mvc.spring.web.services.event.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import mvc.spring.web.dto.EventDto;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public EventDto save(Long clubId, EventDto eventDto) {
        Optional<Club> foundClub = clubRepository.findById(clubId);
        Club club = foundClub.get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
        return mapToEventDto(event);
    }

    @Override
    public EventDto findById(Long id) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void update(EventDto event) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    private Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .name(eventDto.getName())
                .content(eventDto.getContent())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdAt(eventDto.getCreatedAt())
                .updatedAt(eventDto.getUpdatedAt())
                .build();
    }

    private EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .content(event.getContent())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }

}
