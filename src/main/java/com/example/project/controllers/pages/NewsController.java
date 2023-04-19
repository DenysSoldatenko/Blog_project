package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Article;
import com.example.project.models.Items;
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
    String requestUrl = req.getRequestURI();
    Items<Article> items = null;
    if (requestUrl.endsWith("/news") || requestUrl.endsWith("/news/")) {
      items = getBusinessService().listArticles(0, Constant.LIMIT_ARTICLES_PER_PAGE);
    }  //TODO display articles for category

    req.setAttribute("list", items.getItems());
    forwardToPage("news.jsp", req, resp);
  }
}
