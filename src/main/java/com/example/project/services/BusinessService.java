package com.example.project.services;

import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.models.Items;
import java.util.Map;

/**
 * Interface for defining business services.
 */
public interface BusinessService {
  Map<Integer, Category> mapCategories();

  Items<Article> listArticles(int offset, int limit);
}
