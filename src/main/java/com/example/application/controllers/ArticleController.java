package com.example.application.controllers;

import com.example.application.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

  private final ArticleDAO articleDAO;

  @Autowired
  public ArticleController(ArticleDAO articleDAO) {
    this.articleDAO = articleDAO;
  }

  @GetMapping("/blog")
  public String index(Model model) {
    model.addAttribute("articles", articleDAO.index());
    return "index";
  }
}

