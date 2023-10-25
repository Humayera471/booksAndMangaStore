package com.cse471.booksAndMangaStore.entity.user;

import com.cse471.booksAndMangaStore.constant.TableNameConstant;
import com.cse471.booksAndMangaStore.entity.BaseEntity;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = TableNameConstant.USER_CREDENTIAL_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@NamedEntityGraph(
    name = "update-role",
    attributeNodes = {@NamedAttributeNode("roleEntities")})
public class UserCredentialEntity extends BaseEntity {
  @Email
  @Column(unique = true)
  private String email;

  private String password;

  @Column(unique = true)
  private String userName;

  private boolean active = true;
  private boolean isAccountNonExpired = false;
  private boolean isAccountNonLocked = true;
  private boolean isCredentialsNonExpired = false;
  private boolean isEnabled = true;

  @ManyToMany
  @JoinTable(
      name = "map_user_credential__role",
      joinColumns = {@JoinColumn(name = "user_credential_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<RoleEntity> roleEntities = new HashSet<>();
}
