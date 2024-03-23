package mvc.spring.web.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClubDto {

    private Long id;

    @NotEmpty(message = "Club title is required")
    private String title;

    @NotEmpty(message = "photo url is required")
    private String photoUrl;

    @NotEmpty(message = "Club description is required")
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<EventDto> events = new ArrayList<>();
}
