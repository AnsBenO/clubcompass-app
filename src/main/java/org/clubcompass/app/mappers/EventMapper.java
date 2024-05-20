package org.clubcompass.app.mappers;

import org.clubcompass.app.dto.EventDto;
import org.clubcompass.app.dto.PartialClubDto;
import org.clubcompass.app.models.Club;
import org.clubcompass.app.models.Event;

public class EventMapper {

    private EventMapper() {
    }

    public static Event mapToEvent(EventDto eventDto) {
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

    public static EventDto mapToEventDto(Event event) {
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
                .club(mapToPartialClubDto(event.getClub()))
                .build();
    }

    public static PartialClubDto mapToPartialClubDto(Club club) {
        return PartialClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .createdBy(club.getCreatedBy().getUsername())
                .build();
    }

}
