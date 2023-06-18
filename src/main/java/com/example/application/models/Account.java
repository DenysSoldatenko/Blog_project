package com.example.application.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Represents an account entity.
 */
@Data
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  private Long id;

  private String email;

  private String name;

  private String avatar;

  private Timestamp created;
}
