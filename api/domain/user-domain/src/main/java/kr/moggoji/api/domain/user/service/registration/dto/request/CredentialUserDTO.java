package kr.moggoji.api.domain.user.service.registration.dto.request;

import lombok.Getter;

@Getter
public class CredentialUserDTO extends UserDTO {
  private final String password;

  public CredentialUserDTO(String username, String password) {
    super(username);
    this.password = password;
  }
}
