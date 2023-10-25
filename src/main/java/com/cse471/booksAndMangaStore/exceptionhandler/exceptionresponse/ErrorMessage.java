package com.cse471.booksAndMangaStore.exceptionhandler.exceptionresponse;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
  private OffsetDateTime timestamp;
  private String message;
  private String path;

  public ErrorMessage(String message, String path) {
    this.timestamp = OffsetDateTime.now();
    this.message = message;
    this.path = path;
  }
}
