package com.cse471.booksAndMangaStore.controller.role;

import com.cse471.booksAndMangaStore.domain.role.RoleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "role", tags = "role")
@RequestMapping("role")
public interface RoleApi {
  @ApiOperation(
      value = "create a new role",
      nickname = "createNewRole",
      tags = {"role"})
  @PostMapping
  ResponseEntity<Void> createNewRole(@RequestBody RoleRequest roleRequest);

  @ApiOperation(
      value = "update a role description",
      nickname = "createNewRole",
      tags = {"role"})
  @PutMapping("{id}")
  ResponseEntity<Void> updateNewRole(@RequestBody RoleRequest roleRequest, @PathVariable UUID id);
}
