package org.clubcompass.app.dto;

import org.clubcompass.app.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PartialClubDto {
    private Long id;
    private String title;
    private UserEntity createdBy;
}
