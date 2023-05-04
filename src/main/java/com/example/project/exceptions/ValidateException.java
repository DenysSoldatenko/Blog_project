package com.example.project.exceptions;

/**
 * Custom exception class for validation-related exceptions.
 */
public class ValidateException extends Exception {

  public ValidateException(String message) {
    super(message);
  }
}
