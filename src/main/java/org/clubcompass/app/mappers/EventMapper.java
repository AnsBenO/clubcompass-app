package org.clubcompass.app.mappers;

import org.clubcompass.app.dto.EventDto;
import org.clubcompass.app.entities.Event;

public class EventMapper {

    private EventMapper() {
    }

    public static Event mapToEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        return Event.builder()
                .name(eventDto.getName())
                .content(eventDto.getContent())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdAt(eventDto.getCreatedAt())
                .updatedAt(eventDto.getUpdatedAt())
                .club(ClubMapper.mapToClub(eventDto.getClub()))
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        if (event == null) {
            return null;
        }
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
                .club(ClubMapper.mapToPartialClubDto(event.getClub()))
                .build();
    }

}
