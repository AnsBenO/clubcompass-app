package mvc.spring.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.spring.web.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);

}
