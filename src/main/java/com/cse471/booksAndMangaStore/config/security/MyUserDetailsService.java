package com.cse471.booksAndMangaStore.config.security;

import com.cse471.booksAndMangaStore.repository.user.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
  @Autowired private UserCredentialRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(username).orElseThrow();
    return new MyUserDetails(user);
  }
}
