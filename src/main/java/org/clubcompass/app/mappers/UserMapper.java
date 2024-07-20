package org.clubcompass.app.mappers;

import org.clubcompass.app.dto.RegistrationDto;
import org.clubcompass.app.entities.UserEntity;

public class UserMapper {

      private UserMapper() {
      }

      public static UserEntity mapToUserEntity(RegistrationDto userDto) {
            return UserEntity.builder()
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .build();
      }
}
