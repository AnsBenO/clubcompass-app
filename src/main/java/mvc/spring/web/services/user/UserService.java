package mvc.spring.web.services.user;

import mvc.spring.web.dto.RegistrationDto;
import mvc.spring.web.models.UserEntity;

public interface UserService {
    void save(RegistrationDto registration);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
