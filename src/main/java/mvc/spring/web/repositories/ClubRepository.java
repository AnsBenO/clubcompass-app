package mvc.spring.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mvc.spring.web.models.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String title);

    @Query("SELECT c FROM Club c WHERE LOWER(c.title) LIKE CONCAT('%', LOWER(:query) ,'%')")
    List<Club> searchClub(String query);

}
