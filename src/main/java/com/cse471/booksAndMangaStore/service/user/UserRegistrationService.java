package com.cse471.booksAndMangaStore.service.user;

import com.cse471.booksAndMangaStore.domain.credential.RegistrationRequest;
import com.cse471.booksAndMangaStore.domain.credential.RegistrationResponse;
import com.cse471.booksAndMangaStore.domain.user.UserInformationResponse;
import com.cse471.booksAndMangaStore.exceptionhandler.customexception.UserAlreadyExistException;
import com.cse471.booksAndMangaStore.mapper.UserRegistrationMapper;
import com.cse471.booksAndMangaStore.repository.user.UserCredentialRepository;
import com.cse471.booksAndMangaStore.repository.user.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
  private final UserCredentialRepository userCredentialRepository;
  private final UserInformationRepository userInformationRepository;
  private final UserRegistrationMapper userRegistrationMapper;
  private final PasswordEncoder passwordEncoder;

  public RegistrationResponse saveUser(RegistrationRequest registrationRequest) {
    if (userCredentialRepository.existsByEmail(registrationRequest.getEmail()))
      throw new UserAlreadyExistException(registrationRequest.getEmail());
    var userInformationEntity =
        userInformationRepository.save(
            userRegistrationMapper.userInformationDomainToEntity(
                registrationRequest, passwordEncoder));
    return userRegistrationMapper.entityToDomain(userInformationEntity.getUserCredentialEntity());
  }

  public Page<UserInformationResponse> getAllUser(Pageable pageable) {
    return userInformationRepository
        .findAll(pageable)
        .map(userRegistrationMapper::userInformationEntityToResponse);
  }
}
