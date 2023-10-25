package com.cse471.booksAndMangaStore.domain.credential;

import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class LoginResponse {
  private String userName;
  private String jwtToken;
  private Set<String> roles;
  private Instant expiredDate;
  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String presetAddress;
  private String permanentAddress;
}
