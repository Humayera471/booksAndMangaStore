package com.cse471.booksAndMangaStore.controller.user.registration;

import com.cse471.booksAndMangaStore.domain.credential.RegistrationRequest;
import com.cse471.booksAndMangaStore.domain.credential.RegistrationResponse;
import com.cse471.booksAndMangaStore.domain.user.UserInformationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "user", tags = "user-registration")
@RequestMapping("/user/registration")
public interface UserRegistrationApi {
  @ApiOperation(
      value = "Get all user",
      nickname = "getAllUser",
      notes = "Get all user",
      tags = {
        "user-registration",
      })
  @GetMapping
  ResponseEntity<Page<UserInformationResponse>> getAllUser(
      Pageable pageable,
      @RequestParam(value = "unpaged", required = false) final boolean unPaged,
      @RequestParam(value = "isActive", required = false) boolean isActive);

  @ApiOperation(
      value = "Create new user",
      nickname = "addFleetRules",
      notes = "Add new user to the system",
      tags = {
        "user-registration",
      })
  @PostMapping
  ResponseEntity<RegistrationResponse> createNewUser(
      @RequestBody RegistrationRequest registrationRequest);
}
