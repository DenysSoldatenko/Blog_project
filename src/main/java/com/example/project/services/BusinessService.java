package com.example.project.services;

import com.example.project.entities.Category;
import java.util.Map;

/**
 * Interface for defining business services.
 */
public interface BusinessService {
  Map<Integer, Category> mapCategories();
}
