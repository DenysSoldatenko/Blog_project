package com.example.project.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a collection of items with a count.
 *
 * @param <T> the type of items in the collection
 */
@Getter
@Setter
public class Items<T> extends AbstractModel {
  private List<T> items;
  private int count;
}
