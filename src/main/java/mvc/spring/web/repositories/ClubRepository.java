package mvc.spring.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.spring.web.models.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String title);
}
