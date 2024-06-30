package org.clubcompass.app.repositories;

import java.util.List;

import org.clubcompass.app.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
            SELECT e
            FROM Event e
            WHERE LOWER(e.name)
            LIKE CONCAT('%', LOWER(:query) ,'%')
            """)
    List<Event> searchEvent(String query);

    @Query("SELECT e.type FROM Event e")
    List<String> getEventTypes();
}
