package kr.moggoji.api.core.configuration.security.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {
  private final String accessToken;
  private final Long accessTokenExpiration;
}
