package com.cse471.booksAndMangaStore.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners({AuditingEntityListener.class, CustomAuditingEntityListener.class})
public class BaseEntity implements Serializable {
  @Id
  @Column(name = "id", insertable = false, updatable = false, nullable = false)
  @Type(type = "uuid-char")
  private UUID id;

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedBy private String updatedBy;

  @CreatedDate
  @Column(name = "CREATE_DATE_TIME", updatable = false)
  private OffsetDateTime createDateTime;

  @LastModifiedDate
  @Column(name = "UPDATE_DATE_TIME")
  private OffsetDateTime updateDateTime;

  @Setter
  @Column(name = "IS_DELETED")
  private boolean deleted;
}
