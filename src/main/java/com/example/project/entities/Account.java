package com.example.project.entities;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an account entity.
 */
@Getter
@Setter
public class Account extends AbstractEntity<Long> {

  private String email;
  private String name;
  private String avatar;
  private Timestamp created;

  public boolean isAvatarExists() {
    return avatar != null;
  }

  public String getNoAvatar() {
    return "/static/img/no_avatar.png";
  }
}
