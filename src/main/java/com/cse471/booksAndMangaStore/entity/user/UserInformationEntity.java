package com.cse471.booksAndMangaStore.entity.user;

import com.cse471.booksAndMangaStore.constant.TableNameConstant;
import com.cse471.booksAndMangaStore.entity.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = TableNameConstant.USER_INFORMATION_TABLE)
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationEntity extends BaseEntity {
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "present_address")
  private String presetAddress;

  @Column(name = "permanent_address")
  private String permanentAddress;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "map_user_info__credential",
      joinColumns = @JoinColumn(name = "user_information_id"),
      inverseJoinColumns = @JoinColumn(name = "user_credential_id"))
  private UserCredentialEntity userCredentialEntity;
}
