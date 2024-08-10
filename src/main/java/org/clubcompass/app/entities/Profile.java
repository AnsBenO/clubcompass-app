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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;

      @Builder.Default
      private String profilePictureUrl = "/assets/Profile_PlaceHolder.png";

      private String about;

      @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
      @JoinColumn(name = "user_id", referencedColumnName = "id")
      private UserEntity user;
}
