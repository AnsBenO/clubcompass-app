package org.clubcompass.app.dto;

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
public class ProfileDto {
      private Long id;
      private String profilePictureUrl;
      private String about;
      private String username;
      private String email;

}
