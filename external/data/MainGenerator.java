package com.example.project.data;

import com.example.project.data.ArticleGenerator.ArticleItem;
import com.example.project.data.CategoryGenerator.CategoryItem;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class for generating and inserting data into the database.
 */
public class MainGenerator {
  private static final Logger LOGGER = Logger.getLogger(MainGenerator.class.getName());

  /**
   * The main method for generating and inserting data into the database.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    init();
    deleteMediaDir();

    try (Connection connection = DriverManager.getConnection(
        Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD)
    ) {
      connection.setAutoCommit(false);
      clearDb(connection);

      List<CategoryItem> categories = CategoryGenerator.generate(connection);
      Constant.accountCount = (Constant.articleCount) / 5;
      System.out.println("TODO: articleCount="
          + Constant.articleCount + ", accountCount=" + Constant.accountCount);

      AccountGenerator.generate(connection);
      List<ArticleItem> articles = ArticleGenerator.generate(connection, categories);
      CommentGenerator.generate(connection, articles);
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error executing SQL queries", e);
      if (e.getNextException() != null) {
        LOGGER.log(Level.SEVERE, "Next exception:", e.getNextException());
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error with file operations", e);
    }
  }

  private static void init() {
    String[] sentences = Constant.DUMMY_CONTENT_TEXT.split("\\.");
    for (String sentence : sentences) {
      sentence = sentence.trim();
      if (!sentence.isEmpty()) {
        Constant.SENTENCES.add(sentence + ".");
      }
    }

    String[] words = Constant.DUMMY_CONTENT_TEXT.split(" ");
    for (String word : words) {
      word = word.replace(",", "").replace(".", "").replace(";", "").trim().toLowerCase();
      if (!Constant.WORDS.contains(word) && word.length() >= 4) {
        Constant.WORDS.add(word);
      }
    }

    Collections.shuffle(Constant.SENTENCES);
    Collections.shuffle(Constant.WORDS);
    System.out.println("SENTENCES SIZE=" + Constant.SENTENCES.size());
    System.out.println("WORDS SIZE=" + Constant.WORDS.size());
  }

  private static void clearDb(Connection c) throws SQLException {
    try (Statement st = c.createStatement()) {
      st.executeUpdate("delete from comments");
      st.executeUpdate("delete from articles");
      st.executeUpdate("delete from accounts");
      st.executeUpdate("delete from categories");
      st.executeQuery("select setval('account_seq', 1, false)");
      st.executeQuery("select setval('comment_seq', 1, false)");
    }
    c.commit();
    System.out.println("Db data deleted");
  }

  private static void deleteFile(File file) {
    if (file.exists()) {
      if (file.isFile()) {
        boolean flag = file.delete();
        if (!flag) {
          LOGGER.log(Level.WARNING, "Failed to delete file: " + file.getAbsolutePath());
        }
      } else if (file.isDirectory()) {
        File[] dir = file.listFiles();
        if (dir != null) {
          for (File f : dir) {
            deleteFile(f);
          }
        }

        boolean flag = file.delete();
        if (!flag) {
          LOGGER.log(Level.WARNING, "Failed to delete file: " + file.getAbsolutePath());
        }
      }
    }
  }

  private static void deleteMediaDir() {
    deleteFile(new File(Constant.DEST_MEDIA));
  }
}