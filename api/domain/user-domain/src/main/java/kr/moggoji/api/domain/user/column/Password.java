package kr.moggoji.api.domain.user.column;

import kr.moggoji.api.core.context.database.Column;
import kr.moggoji.api.core.context.exception.InvalidParameterException;
import kr.moggoji.api.core.encryption.PasswordEncryptor;
import org.springframework.util.ObjectUtils;

public class Password extends Column<String> {
  public static Password plaintext(PasswordEncryptor encryptor, String value) {
    if (ObjectUtils.isEmpty(value)) {
      throw new InvalidParameterException();
    }

    return new Password(encryptor.encrypt(value));
  }

  public static Password hash(String value) {
    return new Password(value);
  }

  Password(String password) {
    this.value = password;
  }
}
