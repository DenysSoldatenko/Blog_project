package com.example.application.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Represents a comment entity.
 */
@Data
@Entity
@Table(name = "comments")
public class Comment {

  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_account", referencedColumnName = "id")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "id_article", referencedColumnName = "id")
  private Article article;

  private String content;

  private Timestamp created;
}
