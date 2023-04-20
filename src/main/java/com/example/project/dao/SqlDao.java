package com.example.project.dao;

import com.example.project.dao.mapper.ArticleMapper;
import com.example.project.dao.mapper.ListMapper;
import com.example.project.dao.mapper.MapCategoryMapper;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
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

  public List<Article> listArticlesByCategory(Connection c, String categoryUrl,
                                              int offset, int limit) throws SQLException {
    return sql.query(c, "select a.* from articles a, "
    + "categories c where c.id=a.id_category "
    + "and c.url=? order by a.id desc limit ? offset ?",
      new ListMapper<>(new ArticleMapper()), categoryUrl, limit, offset);
  }

  public int countArticlesByCategory(Connection c, String categoryUrl) throws SQLException {
    return sql.query(c, "select count(a.id) from articles a, "
    + "categories c where a.id_category=c.id and c.url=?",
      new ScalarHandler<Number>(), categoryUrl).intValue();
  }

  public Category findCategoryByUrl(Connection c, String categoryUrl) throws SQLException {
    return sql.query(c, "select * from categories c where c.url = ?",
      new BeanHandler<>(Category.class), categoryUrl);
  }
}
