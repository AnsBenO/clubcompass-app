package mvc.spring.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PartialClubDto {
    private Long id;
    private String title;

}