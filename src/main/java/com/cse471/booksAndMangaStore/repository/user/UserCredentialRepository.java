package com.cse471.booksAndMangaStore.repository.user;

import com.cse471.booksAndMangaStore.entity.user.UserCredentialEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCredentialRepository extends JpaRepository<UserCredentialEntity, UUID> {
  Optional<UserCredentialEntity> findByEmail(String email);

  @Query(
      value =
          "SELECT DISTINCT uce FROM UserCredentialEntity uce LEFT JOIN FETCH uce.roleEntities WHERE uce.email = :email")
  Optional<UserCredentialEntity> findByEmailEagerly(String email);

  boolean existsByEmail(String email);
}
