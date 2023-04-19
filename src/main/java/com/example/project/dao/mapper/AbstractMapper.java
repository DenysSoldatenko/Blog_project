package com.example.project.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

/**
 * An abstract ResultSetHandler implementation for mapping database rows to entities.
 *
 * @param <T> the type of entity to be mapped
 */
public abstract class AbstractMapper<T> implements ResultSetHandler<T> {
  protected final RowProcessor convert = new BasicRowProcessor();
  boolean shouldBeIterateThroughResultSet = true;

  @Override
  public final T handle(ResultSet rs) throws SQLException {
    if (shouldBeIterateThroughResultSet) {
      if (rs.next()) {
        return handleItem(rs);
      } else {
        return null;
      }
    } else {
      return handleItem(rs);
    }
  }

  public abstract T handleItem(ResultSet rs) throws SQLException;
}
