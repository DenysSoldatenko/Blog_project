package com.example.project.services.impl;

import com.example.project.dao.SqlDao;
import com.example.project.entities.Category;
import com.example.project.exceptions.ApplicationException;
import com.example.project.services.BusinessService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;

class BusinessServiceImpl implements BusinessService {
  private final DataSource dataSource;
  private final SqlDao sql;

  BusinessServiceImpl(ServiceManager serviceManager) {
    super();
    this.dataSource = serviceManager.dataSource;
    this.sql = new SqlDao();
  }

  @Override
  public Map<Integer, Category> mapCategories() {
    try (Connection c = dataSource.getConnection()) {
      return sql.mapCategories(c);
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }
}