package org.clubcompass.app.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "profiles")
public class ProfileEntity {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;

      private String profilePictureUrl;

      private String about;

      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "user_id", referencedColumnName = "id")
      private UserEntity user;
}
