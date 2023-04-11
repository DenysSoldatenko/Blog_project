package com.example.blog_project.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Utility class for generating and inserting account data into the database.
 */
public class AccountGenerator {
  private static final Logger LOGGER = Logger.getLogger(AccountGenerator.class.getName());

  protected static void generate(Connection connection) throws SQLException, IOException {
    List<String> names = new ArrayList<>(Arrays.asList(Constant.NAMES));
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        "insert into accounts values (nextval('account_seq'),?,?,?,?)")) {
      for (int i = 0; i < Constant.accountCount; i++) {
        String name = names.remove(Constant.random.nextInt(names.size()));
        preparedStatement.setString(1, name.toLowerCase() + "@gmail.com");
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, generateAccountAvatar());
        preparedStatement.setTimestamp(4, Constant.generateCreatedTimestamp());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      connection.commit();
      System.out.println("Accounts created: " + Constant.accountCount);
    }
  }

  private static String generateAccountAvatar() throws IOException {
    String uid = UUID.randomUUID() + ".jpg";
    String fileName = Constant.DEST_MEDIA + "\\avatar\\" + uid;
    Constant.downloadImageFromInternet("https://picsum.photos/80/80?people", fileName, LOGGER);
    return "/media/avatar/" + uid;
  }
}
