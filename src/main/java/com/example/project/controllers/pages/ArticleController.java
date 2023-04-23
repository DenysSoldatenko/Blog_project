package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Article;
import com.example.project.exceptions.RedirectToValidUrlException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 * Controller for handling requests related to the "Article" page.
 */
@WebServlet("/article/*")
public class ArticleController extends AbstractController {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    try {
      String requestUrl = req.getRequestURI();
      String id = StringUtils.split(requestUrl, "/")[1];
      Article article = getBusinessService().viewArticle(Long.parseLong(id), requestUrl);
      if (article == null) {
        resp.sendRedirect("/404?url=" + requestUrl);
      } else {
        req.setAttribute("article", article);
        forwardToPage("article.jsp", req, resp);
      }
    } catch (RedirectToValidUrlException e) {
      resp.sendRedirect(e.getUrl());
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      resp.sendRedirect("/news");
    }
  }
}
