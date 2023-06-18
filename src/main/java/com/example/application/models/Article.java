package com.example.application.models;

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

  private String group;

  private String logo;

  private String desc;

  private String content;

  @ManyToOne
  @JoinColumn(name = "id_category", referencedColumnName = "id")
  private Category category;

  private Timestamp created;

  private long views;

  private int comments;
}
