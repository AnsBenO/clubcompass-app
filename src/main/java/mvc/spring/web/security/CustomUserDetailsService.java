package mvc.spring.web.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mvc.spring.web.models.UserEntity;
import mvc.spring.web.repositories.UserRepository;

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
        // Try to find a user by the given username
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user != null) { // If the user was found
            User authUser = new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName().toString()))
                            .collect((Collectors.toList()))); // Convert each role to a Spring Security role
            return authUser; // Return the authenticated user
        } else { // If the user was not found
            throw new UsernameNotFoundException("Invalid Username or Password"); // Throw an exception to indicate that
                                                                                 // the credentials were invalid
        }
    }

}
