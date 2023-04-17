package com.example.project.models;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Abstract base class for model classes.
 */
public abstract class AbstractModel {

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }
}
