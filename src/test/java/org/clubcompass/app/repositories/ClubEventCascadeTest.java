package org.clubcompass.app.repositories;

import org.clubcompass.app.models.Club;
import org.clubcompass.app.models.Event;
import org.clubcompass.app.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ClubEventCascadeTest {

        @Autowired
        private ClubRepository clubRepository;

        @Autowired
        private EventRepository eventRepository;

        @Autowired
        private UserRepository userRepository;

        @Test
        void testCascadePersist() {
                // given
                UserEntity user = UserEntity.builder()
                                .email("creator@example.com")
                                .username("creator")
                                .password("password")
                                .build();
                userRepository.save(user);

                Club club = Club.builder()
                                .title("Test Club")
                                .description("A test club")
                                .photoUrl("clubphoto")
                                .createdBy(user)
                                .build();

                Event event1 = Event.builder()
                                .name("Event 1")
                                .startTime(LocalDateTime.now())
                                .endTime(LocalDateTime.now().plusHours(2))
                                .type("Type1")
                                .photoUrl("url1")
                                .content("content1")
                                .club(club) // set the club reference
                                .build();

                Event event2 = Event.builder()
                                .name("Event 2")
                                .startTime(LocalDateTime.now().plusDays(1))
                                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                                .type("Type2")
                                .photoUrl("url2")
                                .content("content2")
                                .club(club) // set the club reference
                                .build();

                club.setEvents(List.of(event1, event2));

                // when
                Club savedClub = clubRepository.save(club);

                // then
                Optional<Club> retrievedClub = clubRepository.findById(savedClub.getId());
                assertThat(retrievedClub).isPresent();
                assertThat(retrievedClub.get().getEvents()).hasSize(2);
        }

        @Test
        void testCascadeRemove() {
                // given
                UserEntity user = UserEntity.builder()
                                .email("creator@example.com")
                                .username("creator")
                                .password("password")
                                .build();
                userRepository.save(user);

                Event event1 = Event.builder()
                                .name("Event 1")
                                .startTime(LocalDateTime.now())
                                .endTime(LocalDateTime.now().plusHours(2))
                                .type("Type1")
                                .photoUrl("url1")
                                .content("content1")
                                .build();

                Event event2 = Event.builder()
                                .name("Event 2")
                                .startTime(LocalDateTime.now().plusDays(1))
                                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                                .type("Type2")
                                .photoUrl("url2")
                                .content("content2")
                                .build();

                Club club = Club.builder()
                                .title("Test Club")
                                .description("A test club")
                                .photoUrl("clubphoto")
                                .createdBy(user)
                                .events(List.of(event1, event2))
                                .build();

                // Ensure events have the club reference set
                event1.setClub(club);
                event2.setClub(club);

                Club savedClub = clubRepository.save(club);
                assertThat(eventRepository.count()).isEqualTo(2); // Verify events are saved

                // when
                clubRepository.delete(savedClub);

                // then
                Optional<Club> retrievedClub = clubRepository.findById(savedClub.getId());
                assertThat(retrievedClub).isNotPresent();
                assertThat(eventRepository.count()).isZero(); // Verify events are deleted
        }
}
