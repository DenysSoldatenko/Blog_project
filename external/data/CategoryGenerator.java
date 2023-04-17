package com.example.project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Utility class for generating and inserting category data into the database.
 */
public class CategoryGenerator {

  protected static List<CategoryItem> generate(Connection c) throws SQLException {
    List<CategoryItem> categories = new ArrayList<>();
    try (PreparedStatement ps = c.prepareStatement("insert into categories values (?,?,?,?)")) {
      for (int i = 0; i < Constant.CATEGORY_SIZE; i++) {
        String url = Constant.WORDS.get(i);
        String name = Character.toUpperCase(url.charAt(0)) + url.substring(1);
        int articles = generateArticleCount();
        ps.setInt(1, i + 1);
        ps.setString(2, name);
        ps.setString(3, "/" + url);
        ps.setInt(4, articles);
        ps.addBatch();
        categories.add(new CategoryItem(i + 1, articles));
        Constant.articleCount += articles;
      }
      ps.executeBatch();
      c.commit();
    }
    System.out.println("Categories created: " + categories.size());
    return categories;
  }

  private static int generateArticleCount() {
    int diff = Constant.random.nextInt(3);
    switch (diff) {
      case 0: {
        return Constant.random.nextInt(2 * Constant.MIN_ARTICLES_PER_CATEGORY)
            + Constant.MIN_ARTICLES_PER_CATEGORY;
      }
      case 1: {
        return Constant.random.nextInt(Constant.MAX_ARTICLES_PER_CATEGORY / 2)
            + Constant.MIN_ARTICLES_PER_CATEGORY;
      }
      case 2: {
        return Constant.random.nextInt(Constant.MAX_ARTICLES_PER_CATEGORY)
            + Constant.MAX_ARTICLES_PER_CATEGORY / 2;
      }
      default: { }
    }
    throw new IllegalStateException("This exception is not possible");
  }

  /**
   * Represents a category item with associated data.
   */
  @AllArgsConstructor
  protected static class CategoryItem {
    protected final int id;
    protected int articles;

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + id;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      CategoryItem other = (CategoryItem) obj;
      return id == other.id;
    }
  }
}
