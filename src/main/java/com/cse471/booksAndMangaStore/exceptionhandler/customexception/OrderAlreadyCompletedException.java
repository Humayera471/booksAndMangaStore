package com.cse471.booksAndMangaStore.exceptionhandler.customexception;

public class OrderAlreadyCompletedException extends RuntimeException {
  public OrderAlreadyCompletedException(String msg) {
    super(msg);
  }
}
