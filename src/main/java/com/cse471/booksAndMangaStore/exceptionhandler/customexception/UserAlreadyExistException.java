package com.cse471.booksAndMangaStore.exceptionhandler.customexception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException {
  private String name;

  public UserAlreadyExistException(String name) {
    super("User with " + name + " already exists in system.");
  }
}
