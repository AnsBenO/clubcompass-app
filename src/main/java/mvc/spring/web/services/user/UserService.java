package mvc.spring.web.services.user;

import mvc.spring.web.dto.RegistrationDto;

public interface UserService {
    void save(RegistrationDto registration);
}
