package org.clubcompass.app.services.profile;

import java.util.Optional;

import org.clubcompass.app.dto.ProfileDto;
import org.clubcompass.app.entities.Profile;
import org.clubcompass.app.mappers.ProfileMapper;
import org.clubcompass.app.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

      private final ProfileRepository profileRepository;

      @Override
      public ProfileDto findProfileByUsername(String username) throws NotFoundException {
            Optional<Profile> foundProfile = profileRepository.findByUsername(username);
            if (foundProfile.isPresent()) {
                  Profile profile = foundProfile.get();
                  return ProfileMapper.mapToProfileDto(profile);
            }
            throw new NotFoundException("Profile Not Found");
      }
}
