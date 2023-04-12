package com.example.blog_project.data;

import com.example.blog_project.data.CategoryGenerator.CategoryItem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;

/**
 * Utility class for generating and inserting article data into the database.
 */
public class ArticleGenerator {
  private static final Logger LOGGER = Logger.getLogger(ArticleGenerator.class.getName());

  protected static List<ArticleItem> generate(Connection connection, List<CategoryItem> categories)
      throws SQLException, IOException {
    List<ArticleItem> articles = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into articles values (?,?,?,?,?,?,?,?,?,?)")) {
      while (!categories.isEmpty()) {
        setData(connection, preparedStatement, categories, articles);
      }
      preparedStatement.executeBatch();
      connection.commit();
    }
    System.out.println("Articles created: " + articles.size());
    return articles;
  }

  private static void setData(Connection connection, PreparedStatement preparedStatement,
                              List<CategoryItem> categories,
                              List<ArticleItem> articles) throws SQLException, IOException {
    CategoryItem item = categories.get(Constant.random.nextInt(categories.size()));
    String title = Constant.SENTENCES.get(Constant.random.nextInt(Constant.SENTENCES.size()));
    if (title.length() > 80) {
      title = title.substring(0, 80);
    }
    String theme = Constant.IMG_THEME[Constant.random.nextInt(Constant.IMG_THEME.length)];
    String desc = generateArticleDesc(title);
    String content = generateArticleContent(title, desc, theme);
    int comments = Constant.random.nextInt(Constant.accountCount);
    Timestamp created = Constant.generateCreatedTimestamp();

    preparedStatement.setLong(1, Constant.idArticle);
    preparedStatement.setString(2, title);
    preparedStatement.setString(3, createArticleUrl(title));
    preparedStatement.setString(4, generateArticleMainImageLink(theme));
    preparedStatement.setString(5, desc);
    preparedStatement.setString(6, content);
    preparedStatement.setInt(7, item.id);
    preparedStatement.setTimestamp(8, created);
    preparedStatement.setLong(9, Constant.random.nextInt(10000));
    preparedStatement.setInt(10, comments);
    preparedStatement.addBatch();

    articles.add(new ArticleItem(Constant.idArticle, comments, created));
    Constant.idArticle++;
    item.articles--;

    if (item.articles == 0) {
      categories.remove(item);
    }
    if (Constant.idArticle % 20 == 0) {
      System.out.println("Generated " + (Constant.idArticle - Constant.START_ARTICLE_ID)
          + " articles from " + Constant.articleCount);
      preparedStatement.executeBatch();
      connection.commit();
    }
  }

  private static String generateArticleDesc(String title) {
    int repeat = Constant.random.nextInt(5) + 1;
    StringBuilder desc = new StringBuilder("<p>");
    for (int i = 0; i < repeat; i++) {
      desc.append(title).append(" ");
    }
    if (desc.length() >= 255 - 5) {
      desc.delete(255 - 5, desc.length());
    }
    return desc.toString().trim() + "</p>";
  }

  private static String generateArticleContent(String title,
                                               String desc,
                                               String theme) throws IOException {
    boolean withImg = Constant.random.nextBoolean();
    int imgCount = 0;
    if (withImg) {
      imgCount = Constant.random.nextInt(4) + 1;
    }
    int otherSentences = Constant.random.nextInt(14) + 6;
    List<String> sentences = new ArrayList<>();
    sentences.add(title);
    while (sentences.size() != otherSentences + 1) {
      String sentence = Constant.SENTENCES.get(Constant.random.nextInt(Constant.SENTENCES.size()));
      if (!sentences.contains(sentence)) {
        sentences.add(sentence);
      }
    }
    StringBuilder content = new StringBuilder(desc + "\n");
    if (withImg) {
      for (int j = 0; j < imgCount; j++) {
        appendParagraph(content, sentences);
        content.append(generateContentImageHtml(theme));
        appendParagraph(content, sentences);
      }
    } else {
      appendParagraph(content, sentences);
    }
    return content.toString();
  }

  private static void appendParagraph(StringBuilder content, List<String> sentences) {
    int repeat = Constant.random.nextInt(9) + 1;
    for (int i = 0; i < repeat; i++) {
      Collections.shuffle(sentences);
      content.append("<p>");
      List<String> paragraph = sentences.subList(0, Constant.random.nextInt(sentences.size()));
      for (String sentence : paragraph) {
        content.append(sentence).append(" ");
      }
      if (!paragraph.isEmpty()) {
        content.deleteCharAt(content.length() - 1);
      }
      content.append("</p>\n");
    }
  }

  private static String generateContentImageHtml(String theme) throws IOException {
    int w = Constant.random.nextInt(5) * 100 + 200;
    int h = Constant.random.nextInt(10) * 20 + 100;
    String uid = UUID.randomUUID() + ".jpg";
    String fileName = Constant.DEST_MEDIA + "\\" + theme + "\\" + uid;
    Constant.downloadImageFromInternet("https://picsum.photos/" + w + "/" + h + "?" + theme, fileName, LOGGER);
    return "<img class=\"float-center\" src=\"/media/"
      + theme + "/" + uid + "\" alt=\"" + w + "x" + h + "\" /><br/>\n";
  }

  private static String createArticleUrl(String title) {
    return "/" + title.replace(" ", "-").replace(".", "").replace(",", "").toLowerCase().trim();
  }

  private static String generateArticleMainImageLink(String theme) throws IOException {
    String uid = UUID.randomUUID() + ".jpg";
    String fileName = Constant.DEST_MEDIA + "\\" + theme + "\\" + uid;
    Constant.downloadImageFromInternet("https://picsum.photos/1000/400?grayscale" + theme, fileName, LOGGER);
    return "/media/" + theme + "/" + uid;
  }

  /**
   * Represents an article item with associated data.
   */
  @AllArgsConstructor
  protected static class ArticleItem {
    protected final int id;
    protected final int comments;
    protected final Timestamp created;
  }
}
