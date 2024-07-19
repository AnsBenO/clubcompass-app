package org.clubcompass.app.security;

import java.util.stream.Collectors;

import org.clubcompass.app.entities.UserEntity;
import org.clubcompass.app.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Loads the user details by username.
     *
     * @param username the username to load user details
     * @return the UserDetails object based on the user's information
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user != null) {
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                            .collect((Collectors.toList())));
        } else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }

}
