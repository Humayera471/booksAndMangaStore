package com.cse471.booksAndMangaStore.domain.user;

import com.cse471.booksAndMangaStore.domain.BaseDomain;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInformationResponse extends BaseDomain {
  private UUID id;
  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String username;
  private String presetAddress;
  private String permanentAddress;
}
