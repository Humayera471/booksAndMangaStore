package com.cse471.booksAndMangaStore.entity.authority;

import com.cse471.booksAndMangaStore.constant.TableNameConstant;
import com.cse471.booksAndMangaStore.entity.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TableNameConstant.ROLE_TABLE)
@Accessors(chain = true)
public class RoleEntity extends BaseEntity {
  @Column(name = "role_name", unique = true)
  private String name;

  @Column(name = "role_description")
  private String description;

  @ManyToMany
  @JoinTable(
      name = "map_role_authority",
      joinColumns = {@JoinColumn(name = "role_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_id")})
  private Set<AuthorityEntity> authorityEntities = new HashSet<>();
}
