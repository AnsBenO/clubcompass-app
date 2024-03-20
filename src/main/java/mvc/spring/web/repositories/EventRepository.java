package mvc.spring.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.spring.web.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
