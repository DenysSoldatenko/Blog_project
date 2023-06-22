package com.example.application.controllers;

import com.example.application.dao.ArticleDao;
import com.example.application.dao.CategoryDao;
import com.example.application.models.Article;
import com.example.application.utils.BlogService;
import java.util.List;
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

  private final BlogService blogService;

  /**
   * Constructor to initialize the ArticleController with necessary dependencies.
   *
   * @param articleDao   the ArticleDao instance
   * @param categoryDao  the CategoryDao instance
   * @param blogService  the BlogService instance
   */
  @Autowired
  public ArticleController(ArticleDao articleDao,
                           CategoryDao categoryDao, BlogService blogService) {
    this.articleDao = articleDao;
    this.categoryDao = categoryDao;
    this.blogService = blogService;
  }

  /**
   * Handles the "/home" endpoint to display a paginated list of articles.
   *
   * @param pageNumber  the requested page number (default is 1)
   * @param pageSize    the requested page size (default is 12)
   * @param model       the Model object to populate attributes for the view
   * @return the name of the view to render ("index")
   */
  @GetMapping("/home")
  public String home(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                     @RequestParam(value = "size", defaultValue = "12") int pageSize,
                     Model model) {
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
    List<Article> articles = articleDao.getArticles(pageRequest);
    long totalArticles = articleDao.getTotalArticles();

    blogService.setPageAttributes(model, pageNumber, totalArticles, pageSize);
    model.addAttribute("articles", articles);

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

  /**
   * Handles the "/blog/article/{articleTitle}"
   * endpoint to display a single article and its details.
   *
   * @param model        the Model object to populate attributes for the view
   * @param articleTitle the title of the article
   * @return the name of the view to render ("blog-single")
   */
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
   * Handles the "/blog/category/{categoryTitle}"
   * endpoint to display articles by category.
   *
   * @param pageNumber   the requested page number (default is 1)
   * @param pageSize     the requested page size (default is 12)
   * @param categoryTitle the title of the category
   * @param model        the Model object to populate attributes for the view
   * @return the name of the view to render ("portfolio-category")
   */
  @GetMapping("blog/category/{categoryTitle}")
  public String blogCategory(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", defaultValue = "12") int pageSize,
                             @PathVariable String categoryTitle,
                             Model model) {
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
    List<Article> articles = articleDao.getArticlesByCategory(
        categoryTitle.replace('_', ' '), pageRequest);
    long totalArticles = articleDao.getTotalArticlesByCategory(categoryTitle.replace('_', ' '));

    blogService.setPageAttributes(model, pageNumber, totalArticles, pageSize);
    model.addAttribute("categoryName", categoryDao
        .getCategoryName(categoryTitle.replace('_', ' ')));
    model.addAttribute("articles", articles);

    return "portfolio-category";
  }

  /**
   * Handles the "/blog/group/{groupTitle}"
   * endpoint to display articles by article group.
   *
   * @param pageNumber the requested page number (default is 1)
   * @param pageSize   the requested page size (default is 12)
   * @param groupTitle the title of the article group
   * @param model      the Model object to populate attributes for the view
   * @return the name of the view to render ("portfolio-group")
   */
  @GetMapping("blog/group/{groupTitle}")
  public String blogGroup(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                          @RequestParam(value = "size", defaultValue = "12") int pageSize,
                          @PathVariable String groupTitle,
                          Model model) {
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
    List<Article> articles = articleDao.getArticlesByArticleGroup(groupTitle, pageRequest);
    long totalArticles = articleDao.getTotalArticlesByArticleGroup(groupTitle);

    blogService.setPageAttributes(model, pageNumber, totalArticles, pageSize);
    model.addAttribute("groupName", groupTitle);
    model.addAttribute("articles", articles);

    return "portfolio-group";
  }

  /**
   * Handles the "/review" endpoint to process user reviews and comments.
   *
   * @param authorName   the name of the author
   * @param email        the email of the author
   * @param comment      the comment content
   * @param articleId    the ID of the article
   * @param articleTitle the title of the article
   * @param attributes   the RedirectAttributes object for flash attributes
   * @param model        the Model object to populate attributes for the view
   * @return a redirect to the blog article page
   */
  @PostMapping("/review")
  public String review(@RequestParam(value = "author") String authorName,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "comment") String comment,
                       @RequestParam(value = "articleId") Long articleId,
                       @RequestParam(value = "articleTitle") String articleTitle,
                       RedirectAttributes attributes,
                       Model model) {

    if (blogService.processReview(authorName, email, comment, articleId)) {
      attributes.addFlashAttribute("message", "Comment added successfully");
    } else {
      attributes.addFlashAttribute("message", "Check your data!");
    }

    model.addAttribute("author", authorName);
    model.addAttribute("email", email);
    return "redirect:/blog/article/" + articleTitle.replace(' ', '_');
  }
}
