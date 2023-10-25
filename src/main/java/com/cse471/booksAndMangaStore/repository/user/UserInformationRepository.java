package com.cse471.booksAndMangaStore.repository.user;

import com.cse471.booksAndMangaStore.entity.user.UserInformationEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformationEntity, UUID> {
  Optional<UserInformationEntity> findByUserCredentialEntityEmail(String email);
}
