package mvc.spring.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mvc.spring.web.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE LOWER(e.name) LIKE CONCAT('%', LOWER(:query) ,'%')")
    List<Event> searchEvent(String query);

}
