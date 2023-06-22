package com.example.application.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Represents an article entity.
 */
@Data
@Entity
@Table(name = "articles")
public class Article {

  @Id
  private Long id;

  private String title;

  @Column(name = "article_group")
  private String articleGroup;

  private String logo;

  private String description;

  private String content;

  @ManyToOne
  @JoinColumn(name = "id_category", referencedColumnName = "id")
  private Category category;

  private Timestamp created;

  private long views;

  private int comments;
}
