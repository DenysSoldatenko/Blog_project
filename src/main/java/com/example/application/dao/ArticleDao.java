package com.example.application.dao;

import com.example.application.models.Article;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

  /**
   * Retrieves the total number of articles in the database.
   *
   * @return the total number of articles
   */
  public long getTotalArticles() {
    String query = "SELECT COUNT(*) FROM blog_application.public.articles";
    Long count = jdbcTemplate.queryForObject(query, Long.class);
    return count != null ? count : 0;
  }

  /**
   * Retrieves a list of articles based on the provided page request.
   *
   * @param pageRequest the page request object specifying page number and page size
   * @return a list of articles based on the page request
   */
  public List<Article> getArticles(PageRequest pageRequest) {
    int offset = (pageRequest.getPageNumber()) * pageRequest.getPageSize();

    return jdbcTemplate.query(
    """
        SELECT *
        FROM blog_application.public.articles
        ORDER BY created DESC
        LIMIT ? OFFSET ?
    """,
      new BeanPropertyRowMapper<>(Article.class),
    pageRequest.getPageSize(), offset
    );
  }

  /**
   * Retrieves a list of randomly selected articles from the database.
   *
   * @return a list of randomly selected Article objects
   */
  public List<Article> getRandomArticles() {
    return jdbcTemplate.query(
    "SELECT * FROM blog_application.public.articles ORDER BY RANDOM() LIMIT 6",
      new BeanPropertyRowMapper<>(Article.class)
    );
  }

  /**
   * Retrieves a list of titles of the most recent articles from the database.
   *
   * @return a list of recent article titles
   */
  public List<String> getRecentTitles() {
    return jdbcTemplate.query(
    "SELECT title FROM blog_application.public.articles ORDER BY created DESC LIMIT 5",
      new SingleColumnRowMapper<>(String.class)
    );
  }

  /**
   * Retrieves a list of maps
   * containing information about recent comments from the database.
   *
   * @return a list of maps containing recent comment information
   */
  public List<Map<String, Object>> getRecentComments() {
    return jdbcTemplate.query(
    """
    SELECT A.NAME AS ACCOUNT_NAME, AR.TITLE AS ARTICLE_TITLE
    FROM COMMENTS C
    JOIN ACCOUNTS A ON C.ID_ACCOUNT = A.ID
    JOIN ARTICLES AR ON C.ID_ARTICLE = AR.ID
    ORDER BY C.CREATED DESC
    LIMIT 5;
    """,
      new ColumnMapRowMapper()
    );
  }

  /**
   * Retrieves an article by its title from the database.
   *
   * @param title the title of the article to retrieve
   * @return the Article object with the specified title
   */
  public Article getArticle(String title) {
    return jdbcTemplate.queryForObject(
    "SELECT * FROM blog_application.public.articles WHERE title = ?",
      new BeanPropertyRowMapper<>(Article.class),
    title
    );
  }

  /**
   * Retrieves a list of articles based on the provided category and page request.
   *
   * @param categoryTitle the title of the category
   * @param pageRequest   the page request object specifying page number and page size
   * @return a list of articles based on the category and page request
   */
  public List<Article> getArticlesByCategory(String categoryTitle, PageRequest pageRequest) {
    int offset = (pageRequest.getPageNumber()) * pageRequest.getPageSize();
    String categoryIdQuery = "SELECT id FROM categories WHERE name = ?";
    Integer categoryId = jdbcTemplate.queryForObject(categoryIdQuery, Integer.class, categoryTitle);

    if (categoryId != null) {
      String articlesQuery = """
          SELECT *
          FROM blog_application.public.articles
          WHERE id_category = ?
          ORDER BY created DESC
          LIMIT ? OFFSET ?
          """;
      return jdbcTemplate.query(articlesQuery,
        new BeanPropertyRowMapper<>(Article.class),
      categoryId, pageRequest.getPageSize(), offset);
    } else {
      return Collections.emptyList(); // Category isn't found
    }
  }

  /**
   * Retrieves a list of articles based on the provided article group and page request.
   *
   * @param articleGroup the article group to filter by
   * @param pageRequest  the page request object specifying page number and page size
   * @return a list of articles based on the article group and page request
   */
  public List<Article> getArticlesByArticleGroup(String articleGroup, PageRequest pageRequest) {
    int offset = pageRequest.getPageNumber() * pageRequest.getPageSize();
    String articlesQuery = """
        SELECT *
        FROM articles a
        WHERE a.article_group LIKE ? OR a.article_group LIKE ? OR a.article_group LIKE ?
        LIMIT ? OFFSET ?;
        """;

    String likePattern1 = "% " + articleGroup;
    String likePattern2 = "% " + articleGroup + " %";
    String likePattern3 = articleGroup + " %";
    return jdbcTemplate.query(
    articlesQuery,
      new BeanPropertyRowMapper<>(Article.class),
    likePattern1, likePattern2, likePattern3, pageRequest.getPageSize(), offset
    );
  }

  /**
   * Retrieves the total number of articles based on the provided article group.
   *
   * @param articleGroup the article group to filter by
   * @return the total number of articles in the article group
   */
  public long getTotalArticlesByArticleGroup(String articleGroup) {
    String countQuery = """
          SELECT COUNT(*)
          FROM articles a
          WHERE a.article_group LIKE ? OR a.article_group LIKE ? OR a.article_group LIKE ?
          """;

    String likePattern1 = "% " + articleGroup;
    String likePattern2 = "% " + articleGroup + " %";
    String likePattern3 = articleGroup + " %";

    return jdbcTemplate.queryForObject(countQuery, Integer.class,
    likePattern1, likePattern2, likePattern3);
  }

  /**
   * Retrieves the total number of articles based on the provided category.
   *
   * @param categoryTitle the title of the category
   * @return the total number of articles in the category
   */
  public long getTotalArticlesByCategory(String categoryTitle) {
    String categoryIdQuery = "SELECT id FROM categories WHERE name = ?";
    Integer categoryId = jdbcTemplate.queryForObject(categoryIdQuery, Integer.class, categoryTitle);
    Long count = 0L;

    if (categoryId != null) {
      String articlesQuery = """
          SELECT COUNT(*)
          FROM blog_application.public.articles
          WHERE id_category = ?
          """;
      count = jdbcTemplate.queryForObject(articlesQuery, Long.class, categoryId);
    }
    return count != null ? count : 0;
  }

  /**
   * Retrieves a list of comments with associated account information for a specific article title.
   *
   * @param articleTitle the title of the article
   * @return a list of comments with associated account information
   */
  public List<Map<String, Object>> getCommentsWithAccountInfoByTitle(String articleTitle) {
    return jdbcTemplate.queryForList(
    """
        SELECT C.content, C.created, A.name AS account_name, A.avatar AS account_avatar
        FROM comments C
        JOIN accounts A ON C.id_account = A.id
        JOIN articles AR ON C.id_article = AR.id
        JOIN categories CA ON AR.id_category = CA.id
        WHERE AR.title = ?
        ORDER BY C.created DESC
    """,
    articleTitle
    );
  }
}