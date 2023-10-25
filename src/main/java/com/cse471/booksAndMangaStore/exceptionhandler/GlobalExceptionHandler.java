package com.cse471.booksAndMangaStore.exceptionhandler;

import com.cse471.booksAndMangaStore.exceptionhandler.customexception.OrderAlreadyCompletedException;
import com.cse471.booksAndMangaStore.exceptionhandler.customexception.ResourceNotFoundException;
import com.cse471.booksAndMangaStore.exceptionhandler.customexception.TableNotEmptyException;
import com.cse471.booksAndMangaStore.exceptionhandler.exceptionresponse.ErrorMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(
      ResourceNotFoundException ex, HttpServletRequest request) {
    return new ErrorMessage(ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(OrderAlreadyCompletedException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage orderAlreadyCompletedHandler(
      ResourceNotFoundException ex, HttpServletRequest request) {
    return new ErrorMessage(ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage globalExceptionHandler(Exception ex, HttpServletRequest request) {
    return new ErrorMessage(ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(TableNotEmptyException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public ErrorMessage tableNotEmpty(Exception ex, HttpServletRequest request) {
    return new ErrorMessage(ex.getMessage(), request.getRequestURI());
  }
}
