package com.example.project.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for constructing Pagination objects.
 */
public class PaginationBuilder extends AbstractModel {
  private static final int DEFAULT_MAX_PAGINATION_BUTTONS_PER_PAGE = 9;
  private static final int DEFAULT_LIMIT_ITEMS_PER_PAGE = 10;
  private final String baseUrl;
  private final int offset;
  private int limit;
  private final int count;
  private int maxPaginationButtonsPerPage;

  /**
   * Creates a new PaginationBuilder instance.
   *
   * @param baseUrl the base URL for pagination links
   * @param offset  the offset for pagination
   * @param count   the total number of items
   */
  public PaginationBuilder(String baseUrl, int offset, int count) {
    super();
    this.baseUrl = baseUrl;
    this.count = count;
    this.offset = offset;
    this.limit = DEFAULT_LIMIT_ITEMS_PER_PAGE;
    this.maxPaginationButtonsPerPage = DEFAULT_MAX_PAGINATION_BUTTONS_PER_PAGE;
  }

  public PaginationBuilder withLimit(int limit) {
    this.limit = limit;
    return this;
  }

  public PaginationBuilder withMaxPaginationButtonsPerPage(int maxPaginationButtonsPerPage) {
    this.maxPaginationButtonsPerPage = maxPaginationButtonsPerPage;
    return this;
  }

  private List<PageItem> createPageItems(int currentPage, int minPage, int maxPage) {
    List<PageItem> pages = new ArrayList<>();
    for (int page = minPage; page <= maxPage; page++) {
      if (currentPage == page) {
        pages.add(PageItem.createCurrent(String.valueOf(page)));
      } else {
        pages.add(PageItem.createPageItems(createUrlForPage(page), String.valueOf(page)));
      }
    }
    return pages;
  }

  private String createUrlForPage(int page) {
    if (page > 1) {
      return baseUrl + "page=" + page;
    } else {
      // Removes ? or & from url
      return baseUrl.substring(0, baseUrl.length() - 1);
    }
  }

  private String getPreviousUrl(int currentPage) {
    if (currentPage > 1) {
      return baseUrl + "page=" + (currentPage - 1);
    } else {
      return null;
    }
  }

  private String getNextUrl(int currentPage) {
    if (offset + limit < count) {
      return baseUrl + "page=" + (currentPage + 1);
    } else {
      return null;
    }
  }

  private int getMaxPage() {
    int maxPage = count / limit;
    if (count % limit > 0) {
      maxPage++;
    }
    return maxPage;
  }

  private List<PageItem> createButtonsOnly(int currentPage, int maxPage) {
    return createPageItems(currentPage, 1, maxPage);
  }

  private List<PageItem> createButtonsWithLastPageOnly(int currentPage, int maxPage) {
    List<PageItem> pages = createPageItems(currentPage, 1, (maxPaginationButtonsPerPage - 2));
    pages.add(PageItem.createEllipsis());
    pages.add(PageItem.createPageItems(createUrlForPage(maxPage), String.valueOf(maxPage)));
    return pages;
  }

  private List<PageItem> createButtonsWithFirstPageOnly(int currentPage, int maxPage) {
    List<PageItem> pages = new ArrayList<>();
    pages.add(PageItem.createPageItems(createUrlForPage(1), "1"));
    pages.add(PageItem.createEllipsis());
    pages.addAll(createPageItems(currentPage,
        maxPage - (maxPaginationButtonsPerPage - 3), maxPage));
    return pages;
  }

  private List<PageItem> createButtonsWithMiddlePages(int currentPage, int maxPage) {
    int shiftValue = (maxPaginationButtonsPerPage - 5) / 2;
    List<PageItem> pages = new ArrayList<>();
    pages.add(PageItem.createPageItems(createUrlForPage(1), "1"));
    pages.add(PageItem.createEllipsis());
    pages.addAll(createPageItems(currentPage, currentPage - shiftValue, currentPage + shiftValue));
    pages.add(PageItem.createEllipsis());
    pages.add(PageItem.createPageItems(createUrlForPage(maxPage), String.valueOf(maxPage)));
    return pages;
  }

  /**
   * Builds and returns a Pagination object based on the builder configuration.
   *
   * @return a constructed Pagination object
   */
  public Pagination build() {
    if (count <= limit) {
      return null;
    }
    int currentPage = offset / limit + 1;
    String previousUrl = getPreviousUrl(currentPage);
    String nextUrl = getNextUrl(currentPage);
    int maxPage = getMaxPage();
    List<PageItem> pages;
    if (maxPage <= maxPaginationButtonsPerPage) {
      pages = createButtonsOnly(currentPage, maxPage);
    } else {
      int borderValue = (maxPaginationButtonsPerPage - 1) / 2;
      if (currentPage < (maxPaginationButtonsPerPage - borderValue)) {
        pages = createButtonsWithLastPageOnly(currentPage, maxPage);
      } else if (currentPage > maxPage - (maxPaginationButtonsPerPage - borderValue)) {
        pages = createButtonsWithFirstPageOnly(currentPage, maxPage);
      } else {
        pages = createButtonsWithMiddlePages(currentPage, maxPage);
      }
    }
    return new Pagination(previousUrl, nextUrl, pages);
  }
}