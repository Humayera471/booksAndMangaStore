package com.cse471.booksAndMangaStore.entity;

import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class CustomAuditingEntityListener {

  @PrePersist
  public void onPrePersist(BaseEntity baseEntity) {
    if (null == baseEntity.getId()) {
      setUUID(baseEntity);
    }
  }

  @PreUpdate
  public void onPreUpdate(BaseEntity baseEntity) {}

  private void setUUID(BaseEntity baseEntity) {
    baseEntity.setId(UUID.randomUUID());
  }
}
