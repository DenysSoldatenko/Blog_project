package com.example.project.dao.form;

import lombok.Getter;
import lombok.Setter;

/**
 * A form used to submit comments.
 */
@Getter
@Setter
public class CommentForm {
  private Long idArticle;
  private String content;
  private String authToken;
}
