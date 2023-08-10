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
import org.apache.commons.lang.StringUtils;

/**
 * Controller for handling requests related to the "Search" page.
 */
@WebServlet("/search")
public class SearchController extends AbstractController {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    String query = req.getParameter("query");
    if (StringUtils.isNotBlank(query)) {
      Items<Article> items = getBusinessService().listArticlesBySearchQuery(query, 0,
          Constant.LIMIT_ARTICLES_PER_PAGE);
      req.setAttribute("list", items.getItems());
      req.setAttribute("count", items.getCount());
      req.setAttribute("searchQuery", query);
      forwardToPage("search.jsp", req, resp);
    } else {
      resp.sendRedirect("/news");
    }
  }
}
