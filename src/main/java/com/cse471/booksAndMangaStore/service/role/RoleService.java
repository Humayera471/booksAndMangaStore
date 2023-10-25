package com.cse471.booksAndMangaStore.service.role;

import com.cse471.booksAndMangaStore.domain.role.RoleRequest;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import com.cse471.booksAndMangaStore.mapper.RoleMapper;
import com.cse471.booksAndMangaStore.repository.role.AuthorityRepository;
import com.cse471.booksAndMangaStore.repository.role.RoleRepository;
import java.util.HashSet;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final AuthorityRepository authorityRepository;
  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  public void saveRole(RoleRequest role) throws DataIntegrityViolationException {
    var roleEntity = roleMapper.domainToEntityForRequest(role);
    roleEntity.setAuthorityEntities(
        new HashSet<>(authorityRepository.findAllById(role.getListOfAuthority())));
    roleRepository.save(roleEntity);
  }

  public void updateRole(RoleRequest roleRequest, UUID id) {
    RoleEntity roleEntity = roleRepository.findById(id).orElseThrow();
    var updatedRoleEntity = roleMapper.domainToEntityForUpdate(roleRequest, roleEntity);
    updatedRoleEntity.setAuthorityEntities(
        new HashSet<>(authorityRepository.findAllById(roleRequest.getListOfAuthority())));
    roleRepository.save(updatedRoleEntity);
  }
}
