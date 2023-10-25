package com.cse471.booksAndMangaStore.mapper;

import com.cse471.booksAndMangaStore.domain.role.RoleRequest;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
  public RoleEntity domainToEntityForRequest(RoleRequest roleRequest) {
    return new RoleEntity().setName(roleRequest.getRoleName());
  }

  public RoleEntity domainToEntityForUpdate(RoleRequest roleRequest, RoleEntity roleEntity) {
    return roleEntity.setName(roleRequest.getRoleName());
  }
}
