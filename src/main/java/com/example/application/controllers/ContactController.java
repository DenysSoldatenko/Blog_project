package com.example.application.controllers;

import com.example.application.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling contact-related operations and views.
 */
@Controller
public class ContactController {
  private final CategoryDao categoryDao;

  @Autowired
  public ContactController(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @GetMapping("/contact")
  public String index(Model model) {
    model.addAttribute("categories", categoryDao.getCategories());
    return "contact";
  }
}
