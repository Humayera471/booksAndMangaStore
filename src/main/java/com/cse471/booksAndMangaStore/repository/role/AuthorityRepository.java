package com.cse471.booksAndMangaStore.repository.role;

import com.cse471.booksAndMangaStore.entity.authority.AuthorityEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {
  Optional<AuthorityEntity> findByName(String name);

  boolean existsByName(String name);
}
