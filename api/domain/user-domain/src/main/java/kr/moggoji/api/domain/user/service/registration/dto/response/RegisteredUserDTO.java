package kr.moggoji.api.domain.user.service.registration.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class RegisteredUserDTO {
  private final Integer seq;
  private final String username;
  private final String type;
  private final LocalDateTime created;
}
