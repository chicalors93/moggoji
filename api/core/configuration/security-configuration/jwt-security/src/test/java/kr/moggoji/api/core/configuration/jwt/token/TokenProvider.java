package kr.moggoji.api.core.configuration.jwt.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
  private final Key key;

  private final Integer accessTokenExpiration;

  private final Integer refreshTokenExpiration;

  public TokenProvider(String secretKey, Integer accessTokenExpiration, Integer refreshTokenExpiration) {
    byte[] bytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(bytes);

    this.accessTokenExpiration = accessTokenExpiration;
    this.refreshTokenExpiration = refreshTokenExpiration;
  }

  public Token generateToken() {
    long now = (new Date()).getTime();

    String accessToken = Jwts.builder()
            .setExpiration(new Date(now + accessTokenExpiration * 1000L))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    String refreshToken = Jwts.builder()
            .setExpiration(new Date(now + refreshTokenExpiration * 1000L))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    return new Token(accessToken, refreshToken, accessTokenExpiration * 1000L, refreshTokenExpiration * 1000L);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token);

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
