package com.example.project.exceptions;

/**
 * Custom exception class for validation-related exceptions.
 */
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

  public ValidateException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
