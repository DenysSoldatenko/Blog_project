package com.example.project.services;

import java.util.Locale;

/**
 * Service interface for handling internationalization (i18n) messages.
 */
public interface I18nService {
  String getMessage(String key, Locale locale, Object... args);
}
