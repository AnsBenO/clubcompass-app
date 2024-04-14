package mvc.spring.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.spring.web.models.Role;
import mvc.spring.web.models.Role.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);

}
