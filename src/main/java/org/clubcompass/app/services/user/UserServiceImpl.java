package org.clubcompass.app.services.user;

import java.util.Arrays;

import org.clubcompass.app.dto.RegistrationDto;
import org.clubcompass.app.entities.Profile;
import org.clubcompass.app.entities.Role;
import org.clubcompass.app.entities.UserEntity;
import org.clubcompass.app.mappers.UserMapper;
import org.clubcompass.app.entities.Role.RoleName;
import org.clubcompass.app.repositories.ProfileRepository;
import org.clubcompass.app.repositories.RoleRepository;
import org.clubcompass.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    @Override
    public void save(RegistrationDto registration) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registration.getUsername());
        newUser.setEmail(registration.getEmail());
        newUser.setPassword(passwordEncoder.encode(registration.getPassword()));

        Role role = roleRepository.findByName(RoleName.USER);
        newUser.setRoles(Arrays.asList(role));
        userRepository.save(newUser);
        Profile profile = new Profile();
        UserEntity userEntity = newUser;
        profile.setUser(userEntity);
        profileRepository.save(profile);

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
