package kr.moggoji.api.domain.user.entity;

import kr.moggoji.api.domain.user.column.Password;
import kr.moggoji.api.domain.user.column.Username;
import kr.moggoji.api.domain.user.enumerate.UserTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer seq;

  @Column
  private String username;

  public Username getUsername() {
    return new Username(this.username);
  }

  @Column
  private String password;

  @Getter
  @Column
  @Enumerated(EnumType.STRING)
  private UserTypeEnum type;

  @Getter
  @Column
  @CreationTimestamp
  private LocalDateTime created;

  @Getter
  @Column
  @UpdateTimestamp
  private LocalDateTime modified;

  public UserEntity(Username username, Password password, UserTypeEnum type) {
    this.username = username.get();
    this.password = password.get();
    this.type = type;
  }
}
