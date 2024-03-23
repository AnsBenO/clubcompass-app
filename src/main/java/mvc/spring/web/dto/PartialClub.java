package mvc.spring.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PartialClub {
    private Long id;
    private String title;

}