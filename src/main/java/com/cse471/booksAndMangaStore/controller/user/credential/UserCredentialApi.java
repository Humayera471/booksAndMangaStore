package com.cse471.booksAndMangaStore.controller.user.credential;

import com.cse471.booksAndMangaStore.domain.credential.ForgotPasswordAuthenticationResponse;
import com.cse471.booksAndMangaStore.domain.credential.LoginRequest;
import com.cse471.booksAndMangaStore.domain.credential.LoginResponse;
import com.cse471.booksAndMangaStore.domain.credential.PasswordResetCredential;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "user", tags = "user-credential")
@RequestMapping(path = "user/credential")
public interface UserCredentialApi {

  @ApiOperation(
      value = "Login user",
      nickname = "login user",
      notes = "login user",
      tags = {
        "user-credential",
      })
  @PostMapping("login")
  ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest);

  @ApiOperation(
      value = "Generate password recover url",
      nickname = "generatePasswordUrl",
      notes = "this will generate a url for password recovery",
      tags = {
        "user-credential",
      })
  @PostMapping("forgot-password/{userName}")
  ResponseEntity<ForgotPasswordAuthenticationResponse> generatePasswordUrl(
      @PathVariable String userName);

  @ApiOperation(
      value = "Change password",
      nickname = "change password for a specific person",
      notes = "This will reset password.",
      tags = {
        "user-credential",
      })
  @PostMapping("forgot-password/verify/{jwtToken}")
  ResponseEntity<Void> generatePasswordUrl(
      @PathVariable String jwtToken, @RequestBody PasswordResetCredential passwordResetCredential);
}
