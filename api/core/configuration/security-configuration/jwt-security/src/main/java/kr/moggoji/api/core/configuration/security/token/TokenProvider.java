package kr.moggoji.api.core.configuration.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
  private final Key key;

  @Value("${system.configuration.security.jwt.access-token-expiration}")
  private Integer accessTokenExpiration;

  public TokenProvider(@Value("${system.configuration.security.jwt.secret-key}") String secretKey) {
    byte[] bytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(bytes);
  }

  /**
   * 토큰 생성
   *
   * @param audience 수신자(회원을 구분할 수 있는 값)
   *
   * @return Access Token 과 만료 시간
   */
  public Token generateToken(String audience) {
    long now = (new Date()).getTime();

    String accessToken = Jwts.builder()
            .setAudience(audience)
            .setExpiration(new Date(now + accessTokenExpiration * 1000L))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    return new Token(accessToken, accessTokenExpiration * 1000L);
  }

  public Claims getClaims(String token) {
    return parseClaim(token);
  }

  /**
   * 유효한 토큰인지
   *
   * @param token
   *
   * @return
   */
  public boolean isValidToken(String token) {
    try {
      return !parseClaim(token).getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  private Claims parseClaim(String token) {
    try {
      return Jwts.parserBuilder()
              .setSigningKey(key)
              .build().parseClaimsJws(token)
              .getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }
}
