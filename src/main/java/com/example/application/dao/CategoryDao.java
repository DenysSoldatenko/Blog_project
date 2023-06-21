package com.example.application.dao;

import com.example.application.models.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) for managing category-related database operations.
 */
@Component
public class CategoryDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CategoryDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Category> getCategories() {
    return jdbcTemplate.query("SELECT * FROM blog_application.public.categories",
      new BeanPropertyRowMapper<>(Category.class));
  }

  public String getCategoryName(String name) {
    String query = "SELECT name FROM blog_application.public.categories WHERE name LIKE ?";
    return jdbcTemplate.queryForObject(query, String.class, "%" + name + "%");
  }
}
