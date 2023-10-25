package com.cse471.booksAndMangaStore.constant;

import lombok.Getter;

@Getter
public enum RoleNameConstant {
  USER_VIEW("FOOD_VIEW", "User can see food");
  private final String name;
  private final String description;

  RoleNameConstant(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
