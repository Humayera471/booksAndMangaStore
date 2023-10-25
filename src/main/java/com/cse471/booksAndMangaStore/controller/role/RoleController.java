package com.cse471.booksAndMangaStore.controller.role;

import com.cse471.booksAndMangaStore.domain.role.RoleRequest;
import com.cse471.booksAndMangaStore.service.role.RoleService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RoleController implements RoleApi {
  private final RoleService roleService;

  @Override
  public ResponseEntity<Void> createNewRole(RoleRequest roleRequest) {
    roleService.saveRole(roleRequest);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> updateNewRole(RoleRequest roleRequest, UUID id) {
    roleService.updateRole(roleRequest, id);
    return ResponseEntity.ok().build();
  }
}
