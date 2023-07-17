package kr.moggoji.api.core.configuration.jwt.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {
  private final String accessToken;
  private final String refreshToken;
  private final Long accessTokenExpiration;
  private final Long refreshTokenExpiration;
}
