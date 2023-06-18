package com.example.application.external;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class SampleDataGeneratorWithFaker {
  private static final Logger logger = LoggerFactory.getLogger(SampleDataGeneratorWithFaker.class);
  public static void main(String[] args) {
    String jdbcUrl = "jdbc:postgresql://localhost:5432/blog_application";
    String username = "postgres";
    String password = "postgres";

    Faker faker = new Faker();

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      generateCategories(connection, faker);
      generateAccounts(connection, faker);
      generateArticles(connection, faker);
      generateComments(connection, faker);
      connection.close();
    } catch (SQLException e) {
      logger.error("Error while generating sample data: {}", e.getMessage(), e);
    }
  }

  private static void generateCategories(Connection connection, Faker faker) throws SQLException {
    String insertQuery = "INSERT INTO categories (name, articles) VALUES (?, ?)";
    for (int i = 1; i <= 10; i++) {
      try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
        statement.setString(1, faker.cat().name());
        statement.setInt(2, faker.random().nextInt(25));
        statement.executeUpdate();
      }
    }
  }

  private static void generateAccounts(Connection connection, Faker faker) throws SQLException {
    String insertQuery = "INSERT INTO accounts (email, name, avatar, created) VALUES (?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
      for (int i = 1; i <= 10; i++) {
        statement.setString(1, faker.internet().emailAddress());
        statement.setString(2, faker.name().fullName());
        statement.setString(3, "https://picsum.photos/250/300?random=" + faker.random().nextInt(10));
        statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();
      }
    }
  }

  private static void generateArticles(Connection connection, Faker faker) throws SQLException {
    String selectCategoryQuery = "SELECT id, articles FROM categories";
    String insertQuery = "INSERT INTO articles (title, \"group\", logo, \"desc\", content, id_category, created, views, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (
    PreparedStatement selectCategoryStatement = connection.prepareStatement(selectCategoryQuery);
    PreparedStatement statement = connection.prepareStatement(insertQuery)
    ) {
      ResultSet categoryResultSet = selectCategoryStatement.executeQuery();
      while (categoryResultSet.next()) {
        int numComments = categoryResultSet.getInt("articles");

        for (int i = 0; i < numComments; i++) {
          String articleTitle = faker.book().title();
          String articleContent = faker.lorem().paragraph();
          String articleDesc = faker.lorem().sentence();

          String articleGroup = String.join(" ", faker.lorem().words(5));
          if (articleTitle.length() > 25) {
            articleTitle = articleTitle.substring(0, 25);
          }
          if (articleContent.length() > 2500) {
            articleContent = articleContent.substring(0, 2500);
          }
          if (articleDesc.length() > 25) {
            articleDesc = articleDesc.substring(0, 25);
          }
          if (articleGroup.length() > 25) {
            articleGroup = articleGroup.substring(0, 25);
          }

          statement.setString(1, articleTitle);
          statement.setString(2, articleGroup);
          statement.setString(3, "https://picsum.photos/250/300?random=" + faker.random().nextInt(1000));
          statement.setString(4, articleDesc);
          statement.setString(5, articleContent);
          statement.setInt(6, faker.random().nextInt(9) + 1); // Random category ID
          statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
          statement.setLong(8, faker.random().nextLong(1000));
          statement.setInt(9, faker.random().nextInt(100));
          statement.executeUpdate();
        }
      }
    }
  }

  private static void generateComments(Connection connection, Faker faker) throws SQLException {
    String selectArticleQuery = "SELECT id, comments FROM articles";
    String insertQuery = "INSERT INTO comments (id_account, id_article, content, created) VALUES (?, ?, ?, ?)";

    try (
    PreparedStatement selectArticlesStatement = connection.prepareStatement(selectArticleQuery);
    PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
    ) {
      ResultSet articlesResultSet = selectArticlesStatement.executeQuery();
      while (articlesResultSet.next()) {
        int articleId = articlesResultSet.getInt("id");
        int numComments = articlesResultSet.getInt("comments");

        for (int i = 0; i < numComments; i++) {
          insertStatement.setInt(1, faker.random().nextInt(9) + 1); // Random account ID
          insertStatement.setInt(2, articleId);
          insertStatement.setString(3, faker.lorem().sentence());
          insertStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
          insertStatement.executeUpdate();
        }
      }
    }
  }
}
