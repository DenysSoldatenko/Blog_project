package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Article;
import com.example.project.models.Items;
import com.example.project.models.Pagination;
import com.example.project.models.PaginationBuilder;
import com.example.project.utils.Constant;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
      int offset = getOffset(req, Constant.LIMIT_ARTICLES_PER_PAGE);
      Items<Article> items = getBusinessService().listArticlesBySearchQuery(query,
          offset, Constant.LIMIT_ARTICLES_PER_PAGE);
      req.setAttribute("list", items.getItems());
      req.setAttribute("count", items.getCount());
      req.setAttribute("searchQuery", query);
      Pagination pagination = new PaginationBuilder("/search?query="
          + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&",
          offset, items.getCount()).withLimit(Constant.LIMIT_ARTICLES_PER_PAGE).build();
      req.setAttribute("pagination", pagination);
      forwardToPage("search.jsp", req, resp);
    } else {
      resp.sendRedirect("/news");
    }
  }
}
