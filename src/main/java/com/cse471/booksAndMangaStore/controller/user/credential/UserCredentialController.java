package com.cse471.booksAndMangaStore.controller.user.credential;

import com.cse471.booksAndMangaStore.domain.credential.ForgotPasswordAuthenticationResponse;
import com.cse471.booksAndMangaStore.domain.credential.LoginRequest;
import com.cse471.booksAndMangaStore.domain.credential.LoginResponse;
import com.cse471.booksAndMangaStore.domain.credential.PasswordResetCredential;
import com.cse471.booksAndMangaStore.service.user.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserCredentialController implements UserCredentialApi {
  private final UserCredentialService userCredentialService;

  @Override
  public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
    return ResponseEntity.ok(userCredentialService.getAuthentication(loginRequest));
  }

  @Override
  public ResponseEntity<Void> generatePasswordUrl(
      String jwtToken, PasswordResetCredential passwordResetCredential) {
    userCredentialService.resetPassword(jwtToken, passwordResetCredential);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<ForgotPasswordAuthenticationResponse> generatePasswordUrl(String userName) {
    return ResponseEntity.ok(userCredentialService.generateForgotPasswordLink(userName));
  }
}
