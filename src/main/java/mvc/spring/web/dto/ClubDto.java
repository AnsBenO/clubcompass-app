package mvc.spring.web.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.spring.web.models.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClubDto {

    private Long id;

    @NotEmpty(message = "Club title is required")
    private String title;

    @NotEmpty(message = "photo url is required")
    private String photoUrl;

    @NotEmpty(message = "Club description is required")
    private String description;

    private UserEntity createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder.Default
    private List<EventDto> events = new ArrayList<>();
}
