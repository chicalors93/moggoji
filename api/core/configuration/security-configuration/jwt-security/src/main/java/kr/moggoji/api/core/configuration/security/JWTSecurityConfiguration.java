package kr.moggoji.api.core.configuration.security;

import kr.moggoji.api.core.configuration.security.filter.JWTFilter;
import kr.moggoji.api.core.configuration.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class JWTSecurityConfiguration {
  private final TokenProvider tokenProvider;
  private final UserDetailsService userDetailsService;

  @Bean
  public HttpSecurity jwtChain(HttpSecurity http) throws Exception {
    return http
            // token 인증 방식이기에 필요 없음 {
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // }
            .and()
            .addFilterBefore(new JWTFilter(tokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter.class);
  }
}
