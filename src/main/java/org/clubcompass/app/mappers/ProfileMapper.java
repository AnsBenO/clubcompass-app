package org.clubcompass.app.mappers;

import org.clubcompass.app.dto.ProfileDto;
import org.clubcompass.app.entities.Profile;
import org.clubcompass.app.entities.UserEntity;

public class ProfileMapper {
      private ProfileMapper() {
      }

      public static ProfileDto mapToProfileDto(Profile profile) {
            if (profile == null) {
                  return null;
            }
            return ProfileDto.builder()
                        .id(profile.getId())
                        .email(profile.getUser().getEmail())
                        .username(profile.getUser().getUsername())
                        .profilePictureUrl(profile.getProfilePictureUrl())
                        .about(profile.getAbout()).build();
      }

      public static Profile mapToProfile(ProfileDto profileDto) {
            if (profileDto == null) {
                  return null;
            }

            UserEntity user = UserEntity.builder()
                        .username(profileDto.getUsername())
                        .email(profileDto.getEmail())
                        .build();

            return Profile.builder()
                        .id(profileDto.getId())
                        .profilePictureUrl(profileDto.getProfilePictureUrl())
                        .about(profileDto.getAbout())
                        .user(user)
                        .build();
      }

}
