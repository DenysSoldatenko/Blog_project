package com.example.application.controllers;

import com.example.application.dao.ArticleDao;
import com.example.application.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling article-related operations and views.
 */
@Controller
public class ArticleController {

  private final ArticleDao articleDao;

  private final CategoryDao categoryDao;

  @Autowired
  public ArticleController(ArticleDao articleDao, CategoryDao categoryDao) {
    this.articleDao = articleDao;
    this.categoryDao = categoryDao;
  }

  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("articles", articleDao.getArticles());
    model.addAttribute("categories", categoryDao.getCategories());
    return "index";
  }

  @GetMapping("/blog")
  public String blog(Model model) {
    model.addAttribute("categories", categoryDao.getCategories());
    model.addAttribute("articles", articleDao.getRandomArticles());
    model.addAttribute("recentTitles", articleDao.getRecentTitles());
    model.addAttribute("recentComments", articleDao.getRecentComments());
    return "blog";
  }
}

