package com.cse471.booksAndMangaStore.exceptionhandler.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception {
  protected String message;
  protected String customMessage;
  protected String code;
  protected HttpStatus httpStatus;

  public BaseException(String message) {
    super(message);
    this.message = message;
  }
}
