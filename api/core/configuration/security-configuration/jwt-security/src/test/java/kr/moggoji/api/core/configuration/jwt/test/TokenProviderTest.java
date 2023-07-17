package kr.moggoji.api.core.configuration.jwt.test;


import kr.moggoji.api.core.configuration.jwt.token.Token;
import kr.moggoji.api.core.configuration.jwt.token.TokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

  @Test
  void 토큰생성() {
    TokenProvider tokenProvider = getTokenProvider();

    Token token = tokenProvider.generateToken();

    System.out.println("Access-Token: " + token.getAccessToken());
    System.out.println("Refresh-Token: " + token.getRefreshToken());
  }

  @Test
  void 토큰_검증() {
    TokenProvider tokenProvider = getTokenProvider();

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJobW1tbW0iLCJleHAiOjE2ODk1NzkxMzV9.qYUbrkm2jWeYw19Slat07n4qS6PWXRv63XYMErh7IRk";
    String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJobW1tbW0iLCJleHAiOjE2ODk1Nzk0MDV9.lcbJtB_dUR7ekO_Yt7EtQmSRlvjCHk9Hh5di2lyGKjU";

    assertTrue(tokenProvider.validateToken(accessToken), "Access Token 검증 실패");

    assertTrue(tokenProvider.validateToken(refreshToken), "Refresh Token 검증 실패");
  }

  private TokenProvider getTokenProvider() {
    return new TokenProvider("9MTPs2LY0tLEgoEby16yPCY7hqFk2hwbvKVTQN1ZEPPJXQNmQE", 30, 300);
  }
}
