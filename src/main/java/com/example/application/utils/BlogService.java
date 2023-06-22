package com.example.application.utils;

import com.example.application.dao.AccountDao;
import com.example.application.dao.CategoryDao;
import com.example.application.dao.CommentDao;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * Service class for processing reviews and validating account data.
 */
@Service
public class BlogService {
  private final JdbcTemplate jdbcTemplate;

  private final CategoryDao categoryDao;

  private final AccountDao accountDao;

  private final CommentDao commentDao;

  /**
   * Constructor to initialize BlogService with required dependencies.
   *
   * @param jdbcTemplate the JdbcTemplate instance
   * @param categoryDao  the CategoryDao instance
   * @param accountDao   the AccountDao instance
   * @param commentDao   the CommentDao instance
   */
  @Autowired
  public BlogService(JdbcTemplate jdbcTemplate, CategoryDao categoryDao,
                     AccountDao accountDao, CommentDao commentDao) {
    this.jdbcTemplate = jdbcTemplate;
    this.categoryDao = categoryDao;
    this.accountDao = accountDao;
    this.commentDao = commentDao;
  }

  /**
   * Processes a review by inserting an account and a comment into the database.
   *
   * @param authorName the name of the author
   * @param email      the email of the author
   * @param comment    the content of the comment
   * @param articleId  the ID of the article
   * @return true if the review was successfully processed, false otherwise
   */
  @Transactional
  public boolean processReview(String authorName, String email, String comment, Long articleId) {
    if (!check(authorName, email)) {
      return false;
    }

    if (!isEmailExist(email) && !isAccountExist(authorName, email)) {
      accountDao.insertAccount(email, authorName);
    } else if (isEmailExist(email) && !isAccountExist(authorName, email)) {
      return false;
    }

    long accountId = accountDao.getAccountIdByEmail(email);
    commentDao.insertComment(accountId, articleId, comment);
    return true;
  }

  /**
   * Checks if the provided name and email are valid.
   *
   * @param name  the name to check
   * @param email the email to check
   * @return true if both name and email are valid, otherwise false
   */
  public boolean check(String name, String email) {
    boolean isEmailValid = isEmailValid(email);
    boolean isNameValid = isNameValid(name);

    return isEmailValid && isNameValid;
  }

  public boolean isEmailValid(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(emailRegex);
  }

  public boolean isNameValid(String name) {
    return name.length() >= 3 && name.length() <= 30 && name.matches("^[A-Za-z\\s]+$");
  }

  /**
   * Checks if an account with the given email exists in the database.
   *
   * @param email the email to check
   * @return true if an account with the email exists, otherwise false
   */
  public boolean isEmailExist(String email) {
    String sql = "SELECT COUNT(*) FROM accounts WHERE email = ?";

    try {
      Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
      return count != null && count > 0;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  /**
   * Checks if an account with the given name and email exists in the database.
   *
   * @param name  the name to check
   * @param email the email to check
   * @return true if an account with the name and email exists, otherwise false
   */
  public boolean isAccountExist(String name, String email) {
    String sql = "SELECT COUNT(*) FROM accounts WHERE name = ? AND email = ?";

    try {
      Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name, email);
      return count != null && count > 0;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  /**
   * Sets pagination-related attributes in the model.
   *
   * @param model        the Model object to which attributes will be added
   * @param pageNumber  the current page number
   * @param totalArticles the total number of articles available
   * @param pageSize     the number of articles per page
   */
  public void setPageAttributes(Model model, int pageNumber, long totalArticles, int pageSize) {
    int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
    List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());

    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", pageNumber);
    model.addAttribute("pages", pages);
    model.addAttribute("categories", categoryDao.getCategories());
  }
}
