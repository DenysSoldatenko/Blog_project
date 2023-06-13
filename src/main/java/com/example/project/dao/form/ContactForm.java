package com.example.project.dao.form;

import com.example.project.exceptions.ValidateException;
import com.example.project.services.I18nService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * A form used for submitting contact information.
 */
@Getter
@Setter
public class ContactForm extends AbstractForm {
  private String name;
  private String email;
  private String text;

  @Override
  public void validate(I18nService i18nService) throws ValidateException {
    if (!EmailValidator.getInstance().isValid(email)) {
      throw new ValidateException("Email is invalid");
    }
    if (StringUtils.isBlank(name)) {
      throw new ValidateException("Name is required");
    }
    if (StringUtils.isBlank(text)) {
      throw new ValidateException("Text is required");
    }
  }
}
