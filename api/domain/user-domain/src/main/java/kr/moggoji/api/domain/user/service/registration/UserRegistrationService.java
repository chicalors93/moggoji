package kr.moggoji.api.domain.user.service.registration;

import kr.moggoji.api.core.encryption.PasswordEncryptor;
import kr.moggoji.api.domain.user.column.Password;
import kr.moggoji.api.domain.user.column.Username;
import kr.moggoji.api.domain.user.entity.UserEntity;
import kr.moggoji.api.domain.user.enumerate.UserTypeEnum;
import kr.moggoji.api.domain.user.repository.UserDefaultRepository;
import kr.moggoji.api.domain.user.service.registration.dto.request.CredentialUserDTO;
import kr.moggoji.api.domain.user.service.registration.dto.response.RegisteredUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
  private final UserDefaultRepository userDefaultRepository;
  private final PasswordEncryptor passwordEncryptor;

  public RegisteredUserDTO registration(CredentialUserDTO dto) {
    UserEntity user = userDefaultRepository.insert(
            new Username(dto.getUsername()),
            Password.plaintext(passwordEncryptor, dto.getPassword()),
            UserTypeEnum.CREDENTIAL
    );

    return new RegisteredUserDTO(user.getSeq(), user.getUsername().get(), user.getType().name(), user.getCreated());
  }
}
