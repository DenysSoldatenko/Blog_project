package com.example.application.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a category entity.
 */
@Data
@Entity
@Table(name = "categories")
public class Category {

  @Id
  private Long id;

  private String name;

  private int articles;
}
