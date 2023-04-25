package com.example.project.dao.mapper;

import com.example.project.entities.Account;
import com.example.project.entities.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper class for mapping a ResultSet row to a Comment entity.
 */
public class CommentMapper extends AbstractMapper<Comment> {
  private final boolean hasAccountData;

  public CommentMapper(boolean hasAccountData) {
    super();
    this.hasAccountData = hasAccountData;
  }

  @Override
  public Comment handleItem(ResultSet rs) throws SQLException {
    Comment comment = convert.toBean(rs, Comment.class);
    comment.setIdArticle(rs.getLong("id_article"));
    if (hasAccountData) {
      Account account = convert.toBean(rs, Account.class);
      account.setId(rs.getLong("id_account"));
      account.setCreated(rs.getTimestamp("accountCreated"));
      comment.setAccount(account);
    }
    return comment;
  }
}
