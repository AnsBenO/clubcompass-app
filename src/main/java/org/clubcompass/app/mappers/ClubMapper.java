package org.clubcompass.app.mappers;

import java.util.stream.Collectors;

import org.clubcompass.app.dto.ClubDto;
import org.clubcompass.app.models.Club;
import org.jetbrains.annotations.NotNull;

public class ClubMapper {
        private ClubMapper() {
        }

        public static ClubDto mapToClubDto(@NotNull Club club) {
                return ClubDto.builder()
                                .id(club.getId())
                                .title(club.getTitle())
                                .photoUrl(club.getPhotoUrl())
                                .description(club.getDescription())
                                .createdBy(club.getCreatedBy())
                                .createdAt(club.getCreatedAt())
                                .updatedAt(club.getUpdatedAt())
                                .events(club.getEvents().stream()
                                                .map(EventMapper::mapToEventDto)
                                                .collect(Collectors.toList()))
                                .build();
        }

        public static Club mapToClubEntity(@NotNull ClubDto clubDto) {
                return Club.builder()
                                .id(clubDto.getId())
                                .title(clubDto.getTitle())
                                .photoUrl(clubDto.getPhotoUrl())
                                .description(clubDto.getDescription())
                                .createdBy(clubDto.getCreatedBy())
                                .createdAt(clubDto.getCreatedAt())
                                .updatedAt(clubDto.getUpdatedAt())
                                .events(clubDto.getEvents().stream()
                                                .map(EventMapper::mapToEvent)
                                                .collect(Collectors.toList()))
                                .build();
        }
}
