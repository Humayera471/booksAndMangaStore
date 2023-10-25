package com.cse471.booksAndMangaStore.repository.role;

import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
  boolean existsByName(String name);
}
