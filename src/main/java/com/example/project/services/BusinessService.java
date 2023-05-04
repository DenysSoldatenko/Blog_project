package com.example.project.services;

import com.example.project.dao.form.CommentForm;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.entities.Comment;
import com.example.project.exceptions.RedirectToValidUrlException;
import com.example.project.exceptions.ValidateException;
import com.example.project.models.Items;
import java.util.List;
import java.util.Map;

/**
 * Interface for defining business services.
 */
public interface BusinessService {
  Map<Integer, Category> mapCategories();

  Items<Article> listArticles(int offset, int limit);

  Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);

  Category findCategoryByUrl(String categoryUrl);

  Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit);

  Article viewArticle(Long idArticle, String requestUrl) throws RedirectToValidUrlException;

  List<Comment> listComments(long idArticle, int offset, int limit);

  Comment createComment(CommentForm form) throws ValidateException;
}
