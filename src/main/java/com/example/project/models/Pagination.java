package com.example.project.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a pagination component with previous, next, and individual page items.
 */
@Getter
@AllArgsConstructor
public final class Pagination extends AbstractModel {
  private final String previousUrl;
  private final String nextUrl;
  private final List<PageItem> pages;

  public boolean isPrevious() {
    return previousUrl != null;
  }

  public boolean isNext() {
    return nextUrl != null;
  }
}
