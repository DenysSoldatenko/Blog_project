package com.example.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a social media account.
 */
@Getter
@AllArgsConstructor
public final class SocialAccount extends AbstractModel {
  private final String email;
  private final String name;
  private final String avatar;
}
