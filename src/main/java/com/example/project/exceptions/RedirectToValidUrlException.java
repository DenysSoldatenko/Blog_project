package com.example.project.exceptions;

import lombok.Getter;

/**
 * Exception thrown when a redirect to a valid URL is required.
 */
@Getter
public class RedirectToValidUrlException extends Exception {
  private final String url;

  public RedirectToValidUrlException(String url) {
    super("Should be redirect to " + url);
    this.url = url;
  }
}
