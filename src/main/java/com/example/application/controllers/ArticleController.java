package com.example.application.controllers;

import com.example.application.dao.ArticleDao;
import com.example.application.dao.CategoryDao;
import com.example.application.models.Article;
import com.example.application.utils.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handling article-related operations and views.
 */
@Controller
@SessionAttributes({"author", "email"})
public class ArticleController {

  private final ArticleDao articleDao;

  private final CategoryDao categoryDao;

  private final AccountService accountService;

  @Autowired
  public ArticleController(ArticleDao articleDao,
                           CategoryDao categoryDao, AccountService accountService) {
    this.articleDao = articleDao;
    this.categoryDao = categoryDao;
    this.accountService = accountService;
  }


  @GetMapping("/home")
  public String home(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                     @RequestParam(value = "size", defaultValue = "12") int pageSize,
                     Model model) {
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
    List<Article> articles = articleDao.getArticles(pageRequest);
    long totalArticles = articleDao.getTotalArticles();
    int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
    List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());

    model.addAttribute("articles", articles);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", pageNumber);
    model.addAttribute("pages", pages);
    model.addAttribute("categories", categoryDao.getCategories());
    return "index";
  }

  /**
   * Handles the request for the blog page.
   *
   * @param model the Model object for storing data to be displayed in the view
   * @return the name of the view template
   */
  @GetMapping("/blog")
  public String blog(Model model) {
    model.addAttribute("categories", categoryDao.getCategories());
    model.addAttribute("articles", articleDao.getRandomArticles());
    model.addAttribute("recentTitles", articleDao.getRecentTitles());
    model.addAttribute("recentComments", articleDao.getRecentComments());
    return "blog";
  }

  @GetMapping("/blog/article/{articleTitle}")
  public String blogSingle(Model model, @PathVariable String articleTitle) {
    model.addAttribute("categories", categoryDao.getCategories());
    model.addAttribute("article", articleDao.getArticle(articleTitle.replace('_', ' ')));
    model.addAttribute("recentTitles", articleDao.getRecentTitles());
    model.addAttribute("recentComments", articleDao.getRecentComments());
    model.addAttribute("comments",
        articleDao.getCommentsWithAccountInfoByTitle(
        articleTitle.replace('_', ' '))
    );
    return "blog-single";
  }

  /**
   * Handles the request for a blog category page.
   *
   * @param model         the Model object for storing data to be displayed in the view
   * @param categoryTitle the title of the category
   * @return the name of the view template
   */
  @GetMapping("blog/category/{categoryTitle}")
  public String blogCategory(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", defaultValue = "12") int pageSize,
                             @PathVariable String categoryTitle,
                             Model model) {
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
    List<Article> articles = articleDao
        .getArticlesByCategory(
        categoryTitle.replace('_', ' '),
        pageRequest
    );
    long totalArticles = articleDao.getTotalArticlesByCategory(categoryTitle.replace('_', ' '));
    int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
    List<Integer> pages = IntStream.range(0, totalPages).boxed().collect(Collectors.toList());

    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", pageNumber);
    model.addAttribute("pages", pages);
    model.addAttribute("categories", categoryDao.getCategories());
    model.addAttribute("categoryName", categoryDao
        .getCategoryName(categoryTitle.replace('_', ' ')));
    model.addAttribute("articles", articles);
    return "portfolio-category";
  }

  @PostMapping("/review")
  public String review(@RequestParam(value = "author") String authorName,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "comment") String comment,
                       @RequestParam(value = "articleId") Long articleId,
                       @RequestParam(value = "articleTitle") String articleTitle,
                       RedirectAttributes attributes,
                       Model model) {

    if (accountService.processReview(authorName, email, comment, articleId)) {
      attributes.addFlashAttribute("message", "Comment added successfully");
    } else {
      attributes.addFlashAttribute("message", "Check your data!");
    }

    model.addAttribute("author", authorName);
    model.addAttribute("email", email);
    return "redirect:/blog/article/" + articleTitle.replace(' ', '_');
  }
}
