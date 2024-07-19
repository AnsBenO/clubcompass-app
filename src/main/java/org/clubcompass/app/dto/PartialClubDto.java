package org.clubcompass.app.dto;

import org.clubcompass.app.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PartialClubDto {
    private Long id;
    private String title;
    private UserEntity createdBy;
}
