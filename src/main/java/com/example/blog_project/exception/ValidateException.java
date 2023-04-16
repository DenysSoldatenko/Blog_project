package com.example.blog_project.exception;

public class ValidateException extends Exception {

  public ValidateException(String message) {
    super(message);
  }

  public ValidateException(Throwable cause) {
    super(cause);
  }

  public ValidateException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
