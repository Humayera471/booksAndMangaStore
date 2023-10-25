package com.cse471.booksAndMangaStore.config;

import com.cse471.booksAndMangaStore.constant.RoleNameConstant;
import com.cse471.booksAndMangaStore.domain.credential.RegistrationRequest;
import com.cse471.booksAndMangaStore.entity.authority.AuthorityEntity;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import com.cse471.booksAndMangaStore.repository.role.AuthorityRepository;
import com.cse471.booksAndMangaStore.repository.role.RoleRepository;
import com.cse471.booksAndMangaStore.repository.user.UserCredentialRepository;
import com.cse471.booksAndMangaStore.service.user.UserRegistrationService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDatabase implements CommandLineRunner {
  private final AuthorityRepository authorityRepository;
  private final RoleRepository roleRepository;
  private final UserCredentialRepository userCredentialRepository;
  private final UserRegistrationService userRegistrationService;

  @Override
  public void run(String... args) {
    saveAuthority();
    saveRole();
    saveAdmin();
  }

  private void saveAuthority() {
    Set<AuthorityEntity> authorityEntities = new HashSet<>();
    Arrays.stream(RoleNameConstant.values())
        .forEach(
            role -> {
              if (!authorityRepository.existsByName(role.getName())) {
                var authorityEntity =
                    new AuthorityEntity()
                        .setName(role.getName())
                        .setDescription(role.getDescription());
                authorityEntities.add(authorityEntity);
              }
            });
    authorityRepository.saveAll(authorityEntities);
  }

  private void saveRole() {
    if (!roleRepository.existsByName("admin")) {
      var roleEntity =
          new RoleEntity()
              .setName("admin")
              .setAuthorityEntities(new HashSet<>(authorityRepository.findAll()));
      roleRepository.save(roleEntity);
    }
  }

  void saveAdmin() {
    if (!userCredentialRepository.existsByEmail("huri@gmail.com")) {
      var userRegistrationRequest =
          new RegistrationRequest()
              .setFirstName("Huri")
              .setLastName("Admin")
              .setMobileNumber("01516105170")
              .setEmail("huri@gmail.com")
              .setPresetAddress("Dhaka")
              .setPermanentAddress("Dhaka")
              .setPassword("123456");
      userRegistrationService.saveUser(userRegistrationRequest);
      var userEntity = userCredentialRepository.findByEmailEagerly("huri@gmail.com").orElseThrow();
      var roleEntity = roleRepository.findAll();
      userEntity.getRoleEntities().addAll(roleEntity);
      userCredentialRepository.save(userEntity);
    }
  }
}
