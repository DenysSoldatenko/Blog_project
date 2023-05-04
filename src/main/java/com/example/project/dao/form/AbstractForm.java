package com.example.project.dao.form;

import com.example.project.exceptions.ValidateException;
import com.example.project.models.AbstractModel;
import com.example.project.services.I18nService;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * An abstract form class providing common functionality for form objects.
 */
@Getter
@Setter
public abstract class AbstractForm extends AbstractModel {
  protected Locale locale;

  public void validate(I18nService i18nService) throws ValidateException { }
}
