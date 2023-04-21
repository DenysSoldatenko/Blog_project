package com.example.project.services.impl;

import com.example.project.dao.SqlDao;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.exceptions.ApplicationException;
import com.example.project.models.Items;
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

  @Override
  public Items<Article> listArticles(int offset, int limit) {
    try (Connection c = dataSource.getConnection()) {
      Items<Article> items = new Items<>();
      items.setItems(sql.listArticles(c, offset, limit));
      items.setCount(sql.countArticles(c));
      return items;
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }

  @Override
  public Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit) {
    try (Connection c = dataSource.getConnection()) {
      Items<Article> items = new Items<>();
      items.setItems(sql.listArticlesByCategory(c, categoryUrl, offset, limit));
      items.setCount(sql.countArticlesByCategory(c, categoryUrl));
      return items;
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }

  @Override
  public Category findCategoryByUrl(String categoryUrl) {
    try (Connection c = dataSource.getConnection()) {
      return sql.findCategoryByUrl(c, categoryUrl);
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }

  @Override
  public Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit) {
    try (Connection c = dataSource.getConnection()) {
      Items<Article> items = new Items<>();
      items.setItems(sql.listArticlesBySearchQuery(c, searchQuery, offset, limit));
      items.setCount(sql.countArticlesBySearchQuery(c, searchQuery));
      return items;
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }
}
