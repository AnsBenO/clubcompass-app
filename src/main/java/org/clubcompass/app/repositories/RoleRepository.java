package org.clubcompass.app.repositories;

import org.clubcompass.app.entities.Role;
import org.clubcompass.app.entities.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);

}
