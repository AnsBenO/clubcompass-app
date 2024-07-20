package org.clubcompass.app.repositories;

import java.util.Optional;

import org.clubcompass.app.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

      @Query("""
                  SELECT p
                  FROM Profile p JOIN p.user u
                  WHERE u.username = :username
                  """)
      Optional<Profile> findByUsername(String username);
}
