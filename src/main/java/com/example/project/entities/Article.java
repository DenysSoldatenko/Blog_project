package com.example.project.entities;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * Represents an article entity.
 */
@Getter
@Setter
public class Article extends AbstractEntity<Long> {
  private String title;
  private String url;
  private String logo;
  private String desc;
  private String content;
  private int idCategory;
  private Timestamp created;
  private long views;
  private int comments;

  public String getArticleLink() {
    return "/article/" + getId() + url;
  }

  /**
   * Gets a short version of the article title.
   *
   * @return a short version of the article title
   */
  public String getShortTitle() {
    if (StringUtils.length(title) > 20) {
      return title.substring(0, 17) + "...";
    } else {
      return title;
    }
  }
}
