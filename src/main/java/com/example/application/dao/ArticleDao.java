package com.example.application.dao;

import com.example.application.models.Article;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) for managing article-related database operations.
 */
@Component
public class ArticleDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ArticleDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Article> getArticles() {
    return jdbcTemplate.query("SELECT * FROM blog_application.public.articles",
      new BeanPropertyRowMapper<>(Article.class));
  }

  public List<Article> getRandomArticles() {
    return jdbcTemplate.query(
    "SELECT * FROM blog_application.public.articles ORDER BY RANDOM() LIMIT 6",
      new BeanPropertyRowMapper<>(Article.class)
    );
  }

  public List<String> getRecentTitles() {
    return jdbcTemplate.query(
    "SELECT title FROM blog_application.public.articles ORDER BY created DESC LIMIT 5",
      new SingleColumnRowMapper<>(String.class)
    );
  }

  public List<Map<String, Object>> getRecentComments() {
    return jdbcTemplate.query(
    "SELECT A.NAME AS ACCOUNT_NAME, AR.TITLE AS ARTICLE_TITLE FROM COMMENTS C JOIN ACCOUNTS A ON C.ID_ACCOUNT = A.ID JOIN ARTICLES AR ON C.ID_ARTICLE = AR.ID ORDER BY C.CREATED DESC LIMIT 5",
    new ColumnMapRowMapper()
    );
  }
}