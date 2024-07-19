package org.clubcompass.app.services.user;

import org.clubcompass.app.dto.RegistrationDto;
import org.clubcompass.app.entities.UserEntity;

public interface UserService {
    void save(RegistrationDto registration);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
