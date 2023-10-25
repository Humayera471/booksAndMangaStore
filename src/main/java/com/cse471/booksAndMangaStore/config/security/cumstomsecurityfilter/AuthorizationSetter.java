package com.cse471.booksAndMangaStore.config.security.cumstomsecurityfilter;

import com.nimbusds.jose.shaded.json.JSONArray;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class AuthorizationSetter extends OncePerRequestFilter {
  private final JwtDecoder jwtDecoder;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException, BadCredentialsException {
    final String requestTokenHeader = request.getHeader("Authorization");
    String jwtToken = null;
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      try {
        jwtToken = requestTokenHeader.substring(7);
        Collection<? extends GrantedAuthority> grantedAuthorities =
            ((JSONArray) jwtDecoder.decode(jwtToken).getClaims().get("role"))
                .stream()
                    .map(m -> new SimpleGrantedAuthority(m.toString()))
                    .collect(Collectors.toSet());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth =
            new UsernamePasswordAuthenticationToken(
                auth.getPrincipal(), auth.getCredentials(), grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("Expired JWT token");
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }
    chain.doFilter(request, response);
  }
}
