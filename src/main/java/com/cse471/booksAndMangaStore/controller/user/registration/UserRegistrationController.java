package com.cse471.booksAndMangaStore.controller.user.registration;

import com.cse471.booksAndMangaStore.domain.credential.RegistrationRequest;
import com.cse471.booksAndMangaStore.domain.credential.RegistrationResponse;
import com.cse471.booksAndMangaStore.domain.user.UserInformationResponse;
import com.cse471.booksAndMangaStore.service.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRegistrationController implements UserRegistrationApi {
  private final UserRegistrationService userRegistrationService;

  @Override
  public ResponseEntity<RegistrationResponse> createNewUser(
      RegistrationRequest registrationRequest) {
    return new ResponseEntity<>(
        userRegistrationService.saveUser(registrationRequest), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Page<UserInformationResponse>> getAllUser(
      Pageable pageable, boolean unPaged, boolean isActive) {
    return ResponseEntity.ok(userRegistrationService.getAllUser(pageable));
  }
}
