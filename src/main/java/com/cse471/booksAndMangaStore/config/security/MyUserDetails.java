package com.cse471.booksAndMangaStore.config.security;

import com.cse471.booksAndMangaStore.entity.authority.AuthorityEntity;
import com.cse471.booksAndMangaStore.entity.authority.RoleEntity;
import com.cse471.booksAndMangaStore.entity.user.UserCredentialEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {
  private final UserCredentialEntity userCredentialEntity;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userCredentialEntity.getRoleEntities().stream()
        .map(RoleEntity::getAuthorityEntities)
        .flatMap(Collection::stream)
        .map(AuthorityEntity::getName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return this.userCredentialEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return this.userCredentialEntity.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
