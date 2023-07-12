package kr.moggoji.api.core.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptor {
  private final PasswordEncoder encoder;

  /**
   * 비밀번호 암호화
   *
   * @param plaintext 평문 비밀번호
   *
   * @return 암호화 문자열
   */
  public String encrypt(String plaintext) {
    return this.encoder.encode(plaintext);
  }

  /**
   * 비밀번호 검증
   *
   * @param plaintext 평문 비밀번호
   * @param hash 암호화 문자열
   *
   * @return 일치 여부
   */
  public boolean matches(String plaintext, String hash) {
    return this.encoder.matches(plaintext, hash);
  }
}
