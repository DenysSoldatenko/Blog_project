package com.example.project.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a category entity.
 */
@Getter
@Setter
public class Category extends AbstractEntity<Integer> {
  private String name;
  private String url;
  private int articles;
}
