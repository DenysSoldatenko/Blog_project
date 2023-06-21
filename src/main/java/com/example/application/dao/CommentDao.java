package com.example.application.dao;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) for managing comment-related database operations.
 */
@Component
public class CommentDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CommentDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Inserts a new comment into the database.
   *
   * @param accountId  the ID of the account creating the comment
   * @param articleId  the ID of the article the comment is on
   * @param content    the content of the comment
   */
  public void insertComment(Long accountId, Long articleId, String content) {
    String sql = """
        INSERT INTO comments (id_account, id_article, content, created)
        VALUES (?, ?, ?, ?)
        """;
    jdbcTemplate.update(sql,
        accountId, articleId, content, new Timestamp(System.currentTimeMillis()));
  }
}
