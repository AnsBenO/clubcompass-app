package org.clubcompass.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.clubcompass.app.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // given
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .username("testuser")
                .password("password")
                .build();
        userRepository.save(user);

        // when
        UserEntity foundUser = userRepository.findByEmail("test@example.com");

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testFindByUsername() {
        // given
        UserEntity user = UserEntity.builder()
                .email("test2@example.com")
                .username("testuser2")
                .password("password")
                .build();
        userRepository.save(user);

        // when
        UserEntity foundUser = userRepository.findByUsername("testuser2");

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser2");
    }
}
