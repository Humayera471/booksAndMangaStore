package com.cse471.booksAndMangaStore.entity.authority;

import com.cse471.booksAndMangaStore.constant.TableNameConstant;
import com.cse471.booksAndMangaStore.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = TableNameConstant.AUTHORITY_TABLE)
@Accessors(chain = true)
public class AuthorityEntity extends BaseEntity {
  @Column(unique = true)
  @NotNull
  private String name;

  @Column private String description;
}
