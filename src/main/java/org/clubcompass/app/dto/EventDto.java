package org.clubcompass.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class EventDto {
    private Long id;

    @NotEmpty(message = "name shouldn't be empty")
    private String name;

    @NotEmpty(message = "content shouldn't be empty")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @NotNull(message = "start time is required")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @NotNull(message = "end time is required")
    private LocalDateTime endTime;

    @NotEmpty(message = "type shouldn't be empty")
    private String type;

    private String photoUrl;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private PartialClubDto club;
}
