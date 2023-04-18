package com.example.project.dao;

import com.example.project.dao.mapper.MapCategoryMapper;
import com.example.project.entities.Category;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;

/**
 * A data access object (DAO) class for SQL operations.
 */
public final class SqlDao {
  private final QueryRunner sql = new QueryRunner();

  public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
    return sql.query(c, "select * from categories", new MapCategoryMapper());
  }
}
