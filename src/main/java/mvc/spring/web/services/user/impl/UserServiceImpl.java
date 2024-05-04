package mvc.spring.web.services.user.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mvc.spring.web.dto.RegistrationDto;
import mvc.spring.web.models.Role;
import mvc.spring.web.models.UserEntity;
import mvc.spring.web.models.Role.RoleName;
import mvc.spring.web.repositories.RoleRepository;
import mvc.spring.web.repositories.UserRepository;
import mvc.spring.web.services.user.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegistrationDto registration) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registration.getUsername());
        newUser.setEmail(registration.getEmail());
        newUser.setPassword(passwordEncoder.encode(registration.getPassword()));
        Role role = roleRepository.findByName(RoleName.USER);
        newUser.setRoles(Arrays.asList(role));
        userRepository.save(newUser);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Override
    public UserEntity findByUsername(String email) {
        return userRepository.findByUsername(email);

    }

}
