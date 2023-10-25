package com.cse471.booksAndMangaStore.mapper;

import com.cse471.booksAndMangaStore.domain.credential.RegistrationRequest;
import com.cse471.booksAndMangaStore.domain.credential.RegistrationResponse;
import com.cse471.booksAndMangaStore.domain.user.UserInformationResponse;
import com.cse471.booksAndMangaStore.entity.user.UserCredentialEntity;
import com.cse471.booksAndMangaStore.entity.user.UserInformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRegistrationMapper {
  public UserInformationEntity userInformationDomainToEntity(
      RegistrationRequest registrationRequest, PasswordEncoder passwordEncoder) {
    var userCredentialEntity =
        new UserCredentialEntity()
            .setUserName(registrationRequest.getEmail())
            .setPassword(passwordEncoder.encode(registrationRequest.getPassword()))
            .setEmail(registrationRequest.getEmail())
            .setEnabled(true)
            .setActive(true)
            .setAccountNonExpired(true)
            .setAccountNonLocked(true)
            .setCredentialsNonExpired(true);

    return new UserInformationEntity()
        .setUserCredentialEntity(userCredentialEntity)
        .setPresetAddress(registrationRequest.getPresetAddress())
        .setPermanentAddress(registrationRequest.getPermanentAddress())
        .setMobileNumber(registrationRequest.getMobileNumber())
        .setFirstName(registrationRequest.getFirstName())
        .setLastName(registrationRequest.getLastName());
  }

  public UserInformationResponse userInformationEntityToResponse(
      UserInformationEntity userInformationEntity) {
    return new UserInformationResponse()
        .setFirstName(userInformationEntity.getFirstName())
        .setLastName(userInformationEntity.getLastName())
        .setMobileNumber(userInformationEntity.getMobileNumber())
        .setPermanentAddress(userInformationEntity.getPermanentAddress())
        .setPresetAddress(userInformationEntity.getPresetAddress())
        .setUsername(userInformationEntity.getUserCredentialEntity().getUserName())
        .setId(userInformationEntity.getId());
  }

  public RegistrationResponse entityToDomain(UserCredentialEntity userCredentialEntity) {
    return new RegistrationResponse()
        .setEmail(userCredentialEntity.getEmail())
        .setMessage("successfully created user with username : " + userCredentialEntity.getEmail());
  }
}
