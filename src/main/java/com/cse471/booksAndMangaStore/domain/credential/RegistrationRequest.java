package com.cse471.booksAndMangaStore.domain.credential;

import javax.validation.constraints.NotNull;
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
public class RegistrationRequest {
  @NotNull private String email;
  @NotNull private String password;
  @NotNull private String firstName;
  @NotNull private String lastName;
  @NotNull private String mobileNumber;
  @NotNull private String presetAddress;
  @NotNull private String permanentAddress;
}
