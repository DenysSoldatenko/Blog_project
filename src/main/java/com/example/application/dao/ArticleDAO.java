package com.example.application.dao;

import com.example.application.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ArticleDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Article> index() {
    return jdbcTemplate.query("SELECT * FROM blog_application.public.articles",
    new BeanPropertyRowMapper<>(Article.class));
  }
}