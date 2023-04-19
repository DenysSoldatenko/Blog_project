package com.example.project.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

/**
 * A custom list handler that utilizes an AbstractMapper
 * to map ResultSet rows to a list of entities.
 *
 * @param <T> the type of entity to be mapped
 */
public final class ListMapper<T> extends AbstractListHandler<T> {
  private final AbstractMapper<T> handler;

  /**
   * Constructs a ListMapper with the given AbstractMapper.
   *
   * @param handler the AbstractMapper instance to handle mapping of ResultSet rows
   */
  public ListMapper(AbstractMapper<T> handler) {
    super();
    this.handler = handler;
    this.handler.shouldBeIterateThroughResultSet = false;
  }

  @Override
  protected T handleRow(ResultSet rs) throws SQLException {
    return handler.handle(rs);
  }
}
