package org.clubcompass.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.clubcompass.app.models.Role;
import org.clubcompass.app.models.Role.RoleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        // given
        Role role = Role.builder()
                .name(RoleName.ADMIN)
                .build();
        roleRepository.save(role);

        // when
        Role foundRole = roleRepository.findByName(RoleName.ADMIN);

        // then
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo(RoleName.ADMIN);
    }
}
