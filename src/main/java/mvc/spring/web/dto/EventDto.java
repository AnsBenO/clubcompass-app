package mvc.spring.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EventDto {
    private Long id;

    private String name;

    private String content;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String type;

    private String photoUrl;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private PartialClubDto club;
}
