package com.example.project.entities;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a comment entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractEntity<Long> {
  private Long idArticle;
  private Account account;
  private String content;
  private Timestamp created;
}
