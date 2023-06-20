package com.example.application.controllers;

import com.example.application.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling about-related operations and views.
 */
@Controller
public class AboutController {
  private final CategoryDao categoryDao;

  @Autowired
  public AboutController(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @GetMapping("/about")
  public String index(Model model) {
    model.addAttribute("categories", categoryDao.getCategories());
    return "about";
  }
}
