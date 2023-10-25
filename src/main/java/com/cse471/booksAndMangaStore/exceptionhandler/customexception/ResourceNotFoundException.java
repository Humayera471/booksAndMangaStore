package com.cse471.booksAndMangaStore.exceptionhandler.customexception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
