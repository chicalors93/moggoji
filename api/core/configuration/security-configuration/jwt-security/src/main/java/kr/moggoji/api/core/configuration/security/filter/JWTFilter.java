package kr.moggoji.api.core.configuration.security.filter;

import kr.moggoji.api.core.configuration.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends GenericFilter {
  private final TokenProvider tokenProvider;
  private final UserDetailsService userDetailsService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String accessToken = getAccessToken((HttpServletRequest) request);

    if (accessToken != null && tokenProvider.isValidToken(accessToken)) {
      UserDetails user = userDetailsService.loadUserByUsername(tokenProvider.getClaims(accessToken).getAudience());

      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, accessToken, user.getAuthorities()));
    }

    chain.doFilter(request, response);
  }

  private String getAccessToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");

    if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
      return token.substring(7);
    }

    return null;
  }
}
