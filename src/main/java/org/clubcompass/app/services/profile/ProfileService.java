package org.clubcompass.app.services.profile;

import org.clubcompass.app.dto.ProfileDto;

import javassist.NotFoundException;

public interface ProfileService {
      ProfileDto findProfileByUsername(String username) throws NotFoundException;

}
