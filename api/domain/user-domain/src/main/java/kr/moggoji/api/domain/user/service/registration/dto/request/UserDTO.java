package kr.moggoji.api.domain.user.service.registration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserDTO {
  protected String username;
}
