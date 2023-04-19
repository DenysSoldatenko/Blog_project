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
}
