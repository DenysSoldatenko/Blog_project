package com.example.project.services.impl;

import com.example.project.dao.form.CommentForm;
import com.example.project.entities.Account;
import com.example.project.entities.Article;
import com.example.project.entities.Comment;
import com.example.project.exceptions.ApplicationException;
import com.example.project.exceptions.ValidateException;
import com.example.project.models.SocialAccount;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

class DemoBusinessService extends BusinessServiceImpl {

  DemoBusinessService(ServiceManager serviceManager) {
    super(serviceManager);
  }

  @Override
  public Comment createComment(CommentForm form) throws ValidateException {
    form.validate(i18nService);
    try (Connection c = dataSource.getConnection()) {
      SocialAccount socialAccount = socialService.getSocialAccount(form.getAuthToken());
      Account account = new Account();
      account.setId(0L);
      account.setAvatar(socialAccount.getAvatar());
      account.setCreated(new Timestamp(System.currentTimeMillis()));
      account.setEmail(socialAccount.getEmail());
      account.setName(socialAccount.getName());
      Comment comment = new Comment(form.getIdArticle(), account,
          form.getContent(), new Timestamp(System.currentTimeMillis()));
      Article article = sql.findArticleForNewCommentNotification(c, form.getIdArticle());
      sendNewCommentNotification(article, form.getContent(), form.getLocale());
      return comment;
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }
}
