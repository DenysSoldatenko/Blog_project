package com.example.blog_project.data;

import com.example.blog_project.data.ArticleGenerator.ArticleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for generating and inserting comment data into the database.
 */
public class CommentGenerator {

  protected static void generate(Connection c, List<ArticleItem> articles) throws SQLException {
    int count = 0;
    try (PreparedStatement ps = c.prepareStatement(
        "insert into comments values (nextval('comment_seq'),?,?,?,?)")) {
      for (ArticleItem item : articles) {
        if (item.comments > 0) {
          List<Integer> idAccounts = new ArrayList<>();
          for (int i = 0; i < Constant.accountCount; i++) {
            idAccounts.add(i + 1);
          }
          Collections.shuffle(idAccounts);

          Timestamp startTime = item.created;
          Timestamp endTime = new Timestamp(System.currentTimeMillis());
          int intervalBetweenCommentsInDays = (int) (
              ((endTime.getTime() - startTime.getTime())
                                                         / item.comments)
              / Constant.MILLISECONDS_IN_DAY);

          for (int i = 0; i < item.comments; i++) {
            ps.setLong(2, item.id);
            Integer idAccount = idAccounts.remove(Constant.random.nextInt(idAccounts.size()));
            ps.setLong(1, idAccount.longValue());
            ps.setString(3, generateCommentContent());
            Timestamp created = new Timestamp(
                startTime.getTime()
                + Constant.random.nextInt(intervalBetweenCommentsInDays)
                * Constant.MILLISECONDS_IN_DAY);
            ps.setTimestamp(4, randomizeTimeForCreated(created));
            startTime = new Timestamp(
            startTime.getTime()
                 + intervalBetweenCommentsInDays * Constant.MILLISECONDS_IN_DAY);
            ps.addBatch();
            count++;
          }
          ps.executeBatch();
          c.commit();
        }
      }
      System.out.println("Comments created: " + count);
    }
  }

  private static String generateCommentContent() {
    int sentencesCount = Constant.random.nextInt(6) + 1;
    List<String> sentences = new ArrayList<>(Constant.SENTENCES);
    Collections.shuffle(sentences);
    StringBuilder content = new StringBuilder(sentences.remove(0));
    for (int i = 0; i < sentencesCount - 1; i++) {
      content.append(" ").append(sentences.remove(0));
    }
    return content.toString();
  }

  private static Timestamp randomizeTimeForCreated(Timestamp timestamp) {
    Calendar cl = Calendar.getInstance();
    cl.setTimeInMillis(timestamp.getTime());
    cl.add(Calendar.HOUR_OF_DAY, -Constant.random.nextInt(24));
    cl.add(Calendar.MINUTE, -Constant.random.nextInt(60));
    return new Timestamp(cl.getTimeInMillis());
  }
}
