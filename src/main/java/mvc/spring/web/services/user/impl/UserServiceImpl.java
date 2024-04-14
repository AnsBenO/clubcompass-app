package mvc.spring.web.services.user.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import mvc.spring.web.dto.RegistrationDto;
import mvc.spring.web.models.Role;
import mvc.spring.web.models.UserEntity;
import mvc.spring.web.models.Role.RoleName;
import mvc.spring.web.repositories.RoleRepository;
import mvc.spring.web.repositories.UserRepository;
import mvc.spring.web.services.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(RegistrationDto registration) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registration.getUsername());
        newUser.setEmail(registration.getEmail());
        newUser.setPassword(registration.getPassword());
        Role role = roleRepository.findByName(RoleName.USER);
        newUser.setRoles(Arrays.asList(role));
        userRepository.save(newUser);
    }

}
