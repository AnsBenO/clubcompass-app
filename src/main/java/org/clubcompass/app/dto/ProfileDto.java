package org.clubcompass.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProfileDto {
      private Long id;
      private String profilePictureUrl;
      private String about;
      private String username;
      private String email;

}
