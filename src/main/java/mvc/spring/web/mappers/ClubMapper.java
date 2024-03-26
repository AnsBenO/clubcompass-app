package mvc.spring.web.mappers;

import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.models.Club;

public class ClubMapper {
        public static ClubDto mapToClubDto(@NotNull Club club) {
                return ClubDto.builder()
                                .id(club.getId())
                                .title(club.getTitle())
                                .photoUrl(club.getPhotoUrl())
                                .description(club.getDescription())
                                .createdAt(club.getCreatedAt())
                                .updatedAt(club.getUpdatedAt())
                                .events(club.getEvents().stream()
                                                .map((event) -> EventMapper.mapToEventDto(event))
                                                .collect(Collectors.toList()))
                                .build();
        }

        public static Club mapToClubEntity(@NotNull ClubDto clubDto) {
                return Club.builder()
                                .id(clubDto.getId())
                                .title(clubDto.getTitle())
                                .photoUrl(clubDto.getPhotoUrl())
                                .description(clubDto.getDescription())
                                .createdAt(clubDto.getCreatedAt())
                                .updatedAt(clubDto.getUpdatedAt())
                                .events(clubDto.getEvents().stream()
                                                .map((event) -> EventMapper.mapToEvent(event))
                                                .collect(Collectors.toList()))
                                .build();
        }
}
