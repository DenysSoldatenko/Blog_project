package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Article;
import com.example.project.entities.Category;
import com.example.project.models.Items;
import com.example.project.models.Pagination;
import com.example.project.models.PaginationBuilder;
import com.example.project.utils.Constant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for handling requests related to the "News" page.
 */
@WebServlet({"/news", "/news/*"})
public class NewsController extends AbstractController {

  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    int offset = getOffset(req, Constant.LIMIT_ARTICLES_PER_PAGE);
    String requestUrl = req.getRequestURI();
    Items<Article> items;
    if (requestUrl.endsWith("/news") || requestUrl.endsWith("/news/")) {
      items = getBusinessService().listArticles(offset, Constant.LIMIT_ARTICLES_PER_PAGE);
      req.setAttribute("isNewsPage", Boolean.TRUE);
    } else {
      String categoryUrl = requestUrl.replace("/news", "");
      Category category = getBusinessService().findCategoryByUrl(categoryUrl);
      if (category == null) {
        resp.sendRedirect("/404?url=" + requestUrl);
        return;
      }
      items = getBusinessService().listArticlesByCategory(categoryUrl, offset,
          Constant.LIMIT_ARTICLES_PER_PAGE);
      req.setAttribute("selectedCategory", category);
    }
    req.setAttribute("list", items.getItems());
    Pagination pagination = new PaginationBuilder(requestUrl + "?",
        offset, items.getCount()).withLimit(Constant.LIMIT_ARTICLES_PER_PAGE).build();
    req.setAttribute("pagination", pagination);
    forwardToPage("news.jsp", req, resp);
  }
}
