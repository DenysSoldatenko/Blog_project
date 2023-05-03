package com.example.project.services.impl;

import com.example.project.dao.SqlDao;
import com.example.project.dao.form.CommentForm;
import com.example.project.entities.Account;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.entities.Comment;
import com.example.project.exceptions.ApplicationException;
import com.example.project.exceptions.RedirectToValidUrlException;
import com.example.project.models.Items;
import com.example.project.models.SocialAccount;
import com.example.project.services.AvatarService;
import com.example.project.services.BusinessService;
import com.example.project.services.SocialService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BusinessServiceImpl implements BusinessService {
  private final DataSource dataSource;
  private final SqlDao sql;
  private final SocialService socialService;
  private final AvatarService avatarService;
  private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

  BusinessServiceImpl(ServiceManager serviceManager) {
    super();
    this.dataSource = serviceManager.dataSource;
    this.socialService = serviceManager.socialService;
    this.avatarService = serviceManager.avatarService;
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

  @Override
  public Article viewArticle(Long idArticle, String requestUrl) throws RedirectToValidUrlException {
    try (Connection c = dataSource.getConnection()) {
      Article article = sql.findArticleById(c, idArticle);
      if (article == null) {
        return null;
      }
      if (!article.getArticleLink().equals(requestUrl)) {
        throw new RedirectToValidUrlException(article.getArticleLink());
      } else {
        article.setViews(article.getViews() + 1);
        sql.updateArticleViews(c, article);
        c.commit();
        return article;
      }
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }

  @Override
  public List<Comment> listComments(long idArticle, int offset, int limit) {
    try (Connection c = dataSource.getConnection()) {
      return sql.listComments(c, idArticle, offset, limit);
    } catch (SQLException e) {
      throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
    }
  }

  public Comment createComment(CommentForm form) {
    String newAvatarPath = null;
    try (Connection connection = dataSource.getConnection()) {
      SocialAccount socialAccount = socialService.getSocialAccount(form.getAuthToken());
      Account account = sql.findAccountByEmail(connection, socialAccount.getEmail());
      if (account == null) {
        newAvatarPath = avatarService.downloadAvatar(socialAccount.getAvatar());
        account = sql.createNewAccount(
        connection,
        socialAccount.getEmail(),
        socialAccount.getName(),
        newAvatarPath);
      }
      Comment comment = sql.createComment(connection, form, account.getId());
      comment.setAccount(account);
      Article article = sql.findArticleForNewCommentNotification(connection, form.getIdArticle());
      article.setComments(sql.countComments(connection, article.getId()));
      sql.updateArticleComments(connection, article);
      connection.commit();
      // after commit
      //TODO Send new comment notification
      return comment;
    } catch (SQLException | RuntimeException | IOException e) {
      if (avatarService.deleteAvatarIfExists(newAvatarPath)) {
        logger.info("Avatar " + newAvatarPath + " deleted");
      }
      throw new ApplicationException("Can't create new comment: " + e.getMessage(), e);
    }
  }
}
