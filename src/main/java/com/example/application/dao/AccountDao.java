package com.example.application.dao;

import java.sql.Timestamp;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) for managing account-related database operations.
 */
@Component
public class AccountDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AccountDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Long getAccountIdByEmail(String email) {
    String sql = "SELECT id FROM accounts WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, Long.class, email);
  }


  public void insertAccount(String email, String name) {
    String sql = "INSERT INTO accounts (email, name, avatar, created) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, email, name, "https://picsum.photos/250/300?random=" + new Random().nextInt(25), new Timestamp(System.currentTimeMillis()));
  }
}
