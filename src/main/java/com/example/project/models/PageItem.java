package com.example.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an item within a pagination component.
 */
@Getter
@AllArgsConstructor
public final class PageItem extends AbstractModel {
  private final String url;
  private final String caption;

  public boolean isEllipsis() {
    return url == null && caption == null;
  }

  public boolean isCurrent() {
    return url == null && caption != null;
  }

  public boolean isPageItem() {
    return url != null && caption != null;
  }

  static PageItem createCurrent(String caption) {
    return new PageItem(null, caption);
  }

  static PageItem createEllipsis() {
    return new PageItem(null, null);
  }

  static PageItem createPageItems(String url, String caption) {
    return new PageItem(url, caption);
  }
}