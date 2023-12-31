package com.example.project.exceptions;

/**
 * Custom exception class for application-specific exceptions.
 */
public class ApplicationException extends RuntimeException {
  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
