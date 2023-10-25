package com.cse471.booksAndMangaStore.service.user;

import com.cse471.booksAndMangaStore.domain.credential.ForgotPasswordAuthenticationResponse;
import com.cse471.booksAndMangaStore.domain.credential.LoginRequest;
import com.cse471.booksAndMangaStore.domain.credential.LoginResponse;
import com.cse471.booksAndMangaStore.domain.credential.PasswordResetCredential;
import com.cse471.booksAndMangaStore.entity.authority.AuthorityEntity;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import com.cse471.booksAndMangaStore.exceptionhandler.customexception.ResourceNotFoundException;
import com.cse471.booksAndMangaStore.repository.role.AuthorityRepository;
import com.cse471.booksAndMangaStore.repository.user.UserCredentialRepository;
import com.cse471.booksAndMangaStore.repository.user.UserInformationRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCredentialService {
  private final UserCredentialRepository userCredentialRepository;
  private final UserInformationRepository userInformationRepository;
  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;
  private final AuthenticationManager authenticationManager;
  private final AuthorityRepository authorityRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginResponse getAuthentication(LoginRequest loginUser)
      throws BadCredentialsException, InternalAuthenticationServiceException {
    var userInformation =
        this.userInformationRepository
            .findByUserCredentialEntityEmail(loginUser.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("No user found with this email"));
    Instant now = Instant.now();
    Instant expiredTime = now.plus(30, ChronoUnit.DAYS);
    log.info("JWT TOKEN CREATED AT {}", now);
    log.info("JWT TOKEN WILL EXPIRE AT {}", expiredTime);
    String jwtToken =
        generateToken(
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginUser.getEmail(), loginUser.getPassword())),
            now,
            expiredTime);
    return new LoginResponse()
        .setUserName(loginUser.getEmail())
        .setJwtToken(jwtToken)
        .setExpiredDate(expiredTime)
        .setRoles(this.getUserRoles(loginUser.getEmail()))
        .setFirstName(userInformation.getFirstName())
        .setLastName(userInformation.getLastName())
        .setMobileNumber(userInformation.getMobileNumber())
        .setPermanentAddress(userInformation.getPermanentAddress());
  }

  private String generateToken(Authentication authentication, Instant now, Instant expiredTime) {
    System.out.println(authentication.getAuthorities());
    List<String> claim =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuedAt(now)
            .issuer("self")
            .expiresAt(expiredTime)
            .subject(authentication.getName())
            .claim("role", claim)
            .build();
    return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public ForgotPasswordAuthenticationResponse generateForgotPasswordLink(String userName) {
    String jwt = generateTokenForForgetPassword(userName);
    return new ForgotPasswordAuthenticationResponse().setToken(jwt);
  }

  public void resetPassword(String jwtToken, PasswordResetCredential passwordResetCredential) {
    String userName = (String) jwtDecoder.decode(jwtToken).getClaims().get("sub");
    var updatedUser =
        userCredentialRepository
            .findByEmail(userName)
            .orElseThrow()
            .setPassword(passwordEncoder.encode(passwordResetCredential.getNewPassword()));
    userCredentialRepository.save(updatedUser);
  }

  private String generateTokenForForgetPassword(String userName) {
    Instant now = Instant.now();
    Instant expiredTime = now.plus(1, ChronoUnit.HOURS);
    Set<String> claim = getUserAuthorities(userName);

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuedAt(now)
            .issuer("self")
            .expiresAt(expiredTime)
            .subject(userName)
            .claim("role", claim)
            .build();
    return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  private Set<String> getUserAuthorities(String userName) {
    return userCredentialRepository.findByEmail(userName).orElseThrow().getRoleEntities().stream()
        .map(RoleEntity::getAuthorityEntities)
        .flatMap(Collection::stream)
        .map(AuthorityEntity::getName)
        .collect(Collectors.toSet());
  }

  private Set<String> getUserRoles(String userName) {
    return userCredentialRepository.findByEmail(userName).orElseThrow().getRoleEntities().stream()
        .map(RoleEntity::getName)
        .collect(Collectors.toSet());
  }
}
