package com.example.project.dao.form;

import com.example.project.exceptions.ValidateException;
import com.example.project.services.I18nService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * A form used to submit comments.
 */
@Getter
@Setter
public class CommentForm extends AbstractForm {
  private Long idArticle;
  private String content;
  private String authToken;

  @Override
  public void validate(I18nService i18nService) throws ValidateException {
    if (idArticle == null) {
      throw new ValidateException("idArticle is required");
    }
    if (StringUtils.isBlank(content)) {
      throw new ValidateException("content is required");
    }
    if (StringUtils.isBlank(authToken)) {
      throw new ValidateException("authToken is required");
    }
  }
}
