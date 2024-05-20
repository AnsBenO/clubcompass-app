package org.clubcompass.app.services.user.impl;

import java.util.Arrays;

import org.clubcompass.app.dto.RegistrationDto;
import org.clubcompass.app.models.Role;
import org.clubcompass.app.models.UserEntity;
import org.clubcompass.app.models.Role.RoleName;
import org.clubcompass.app.repositories.RoleRepository;
import org.clubcompass.app.repositories.UserRepository;
import org.clubcompass.app.services.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
