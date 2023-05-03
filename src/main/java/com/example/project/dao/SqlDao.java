package com.example.project.dao;

import com.example.project.dao.form.CommentForm;
import com.example.project.dao.mapper.ArticleMapper;
import com.example.project.dao.mapper.CommentMapper;
import com.example.project.dao.mapper.ListMapper;
import com.example.project.dao.mapper.MapCategoryMapper;
import com.example.project.entities.Account;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.entities.Comment;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * A data access object (DAO) class for SQL operations.
 */
public final class SqlDao {
  private final QueryRunner sql = new QueryRunner();

  public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
    return sql.query(c, "select * from categories", new MapCategoryMapper());
  }

  public List<Article> listArticles(Connection c, int offset, int limit) throws SQLException {
    return sql.query(c, "select * from articles a order by a.id desc limit ? offset ?",
      new ListMapper<>(new ArticleMapper()), limit, offset);
  }

  public int countArticles(Connection c) throws SQLException {
    return sql.query(c, "select count(*) from articles a",
      new ScalarHandler<Number>()).intValue();
  }

  /**
   * Retrieves a list of articles
   * belonging to a specific category with pagination.
   *
   * @param c           the database connection
   * @param categoryUrl the URL of the category
   * @param offset      the offset for pagination
   * @param limit       the maximum number of articles to retrieve
   * @return a List of Article objects belonging to the specified category
   * @throws SQLException if a database access error occurs
   */
  public List<Article> listArticlesByCategory(Connection c, String categoryUrl,
                                              int offset, int limit) throws SQLException {
    return sql.query(c, "select a.* from articles a, "
    + "categories c where c.id=a.id_category "
    + "and c.url=? order by a.id desc limit ? offset ?",
      new ListMapper<>(new ArticleMapper()), categoryUrl, limit, offset);
  }

  /**
   * Counts the total number of articles belonging to a specific category.
   *
   * @param c           the database connection
   * @param categoryUrl the URL of the category
   * @return the total number of articles in the specified category
   * @throws SQLException if a database access error occurs
   */
  public int countArticlesByCategory(Connection c, String categoryUrl) throws SQLException {
    return sql.query(c, "select count(a.id) from articles a, "
    + "categories c where a.id_category=c.id and c.url=?",
      new ScalarHandler<Number>(), categoryUrl).intValue();
  }

  public Category findCategoryByUrl(Connection c, String categoryUrl) throws SQLException {
    return sql.query(c, "select * from categories c where c.url = ?",
      new BeanHandler<>(Category.class), categoryUrl);
  }

  /**
   * Retrieves a list of articles matching a search query with pagination.
   *
   * @param c           the database connection
   * @param searchQuery the search query
   * @param offset      the offset for pagination
   * @param limit       the maximum number of articles to retrieve
   * @return a List of Article objects matching the search query
   * @throws SQLException if a database access error occurs
   */
  public List<Article> listArticlesBySearchQuery(Connection c, String searchQuery,
                                                 int offset, int limit) throws SQLException {
    String q = "%" + searchQuery + "%";
    return sql.query(c, "select * from articles a "
    + "where (a.title ilike ? or a.content ilike ?) order by a.id desc limit ? offset ?",
      new ListMapper<>(new ArticleMapper()), q, q, limit, offset);
  }

  /**
   * Counts the total number of articles matching a search query.
   *
   * @param c           the database connection
   * @param searchQuery the search query
   * @return the total number of articles matching the search query
   * @throws SQLException if a database access error occurs
   */
  public int countArticlesBySearchQuery(Connection c, String searchQuery) throws SQLException {
    String q = "%" + searchQuery + "%";
    return new QueryRunner().query(c, "select count(a.id) from articles a "
    + "where (a.title ilike ? or a.content ilike ?)",
      new ScalarHandler<Number>(), q, q).intValue();
  }

  public Article findArticleById(Connection c, long idArticle) throws SQLException {
    return sql.query(c, "select * from articles a where a.id = ?", new ArticleMapper(), idArticle);
  }

  public void updateArticleViews(Connection c, Article article) throws SQLException {
    sql.update(c, "update articles set views=? where id=?", article.getViews(), article.getId());
  }

  /**
   * Retrieves a list of comments for a given article.
   *
   * @param c         The database connection
   * @param idArticle The ID of the article for which to retrieve comments
   * @param offset    The offset for pagination
   * @param limit     The maximum number of comments to retrieve
   * @return A list of comments for the specified article
   * @throws SQLException if a database access error occurs
   */
  public List<Comment> listComments(Connection c, long idArticle,
                                    int offset, int limit) throws SQLException {
    return sql.query(c, "select c.*, a.name, a.email, a.created as accountCreated, a.avatar "
    + "from comments c, accounts a where a.id=c.id_account and c.id_article=? "
    + "order by c.id desc limit ? offset ?",
      new ListMapper<>(new CommentMapper(true)), idArticle, limit, offset);
  }

  public Account findAccountByEmail(Connection c, String email) throws SQLException {
    return sql.query(c, "select * from accounts a where a.email = ?",
      new BeanHandler<>(Account.class), email);
  }

  /**
   * Creates a new account in the database.
   *
   * @param connection The database connection
   * @param email The email of the account
   * @param name The name of the account
   * @param avatar The avatar of the account
   * @return The newly created Account object
   * @throws SQLException if a database access error occurs
   */
  public Account createNewAccount(Connection connection, String email,
                                  String name, String avatar) throws SQLException {
    return sql.insert(connection,
    "insert into accounts(id,email,name,avatar) values(nextval('account_seq'),?,?,?)",
      new BeanHandler<>(Account.class), email, name, avatar);
  }

  /**
   * Creates a new comment in the database.
   *
   * @param connection The database connection
   * @param form The CommentForm containing comment information
   * @param idAccount The ID of the associated account
   * @return The newly created Comment object
   * @throws SQLException if a database access error occurs
   */
  public Comment createComment(Connection connection,
                               CommentForm form, long idAccount) throws SQLException {
    return sql.insert(connection,
    "insert into comments(id, id_article,id_account,content) values(nextval('comment_seq'),?,?,?)",
      new CommentMapper(false), form.getIdArticle(), idAccount, form.getContent());
  }

  /**
   * Finds an article for which a new comment notification is generated.
   *
   * @param connection The database connection
   * @param id The ID of the article
   * @return The Article object representing the found article
   * @throws SQLException if a database access error occurs
   */
  public Article findArticleForNewCommentNotification(Connection connection,
                                                      long id) throws SQLException {
    return sql.query(connection,
    "select a.id, a.id_category, a.url, a.title from articles a where a.id = ?",
      new ArticleMapper(), id);
  }

  /**
   * Counts the number of comments for a specific article.
   *
   * @param connection The database connection
   * @param id The ID of the article
   * @return The count of comments for the specified article
   * @throws SQLException if a database access error occurs
   */
  public int countComments(Connection connection, long id) throws SQLException {
    return sql.query(connection,
    "select count(*) from comments where id_article=?",
      new ScalarHandler<Number>(), id).intValue();
  }

  public void updateArticleComments(Connection c, Article article) throws SQLException {
    sql.update(c, "update articles set comments=? where id=?",
        article.getComments(), article.getId());
  }
}
