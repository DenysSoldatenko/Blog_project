package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Article;
import com.example.project.entities.Comment;
import com.example.project.exceptions.RedirectToValidUrlException;
import com.example.project.utils.Constant;
import java.io.IOException;
import java.util.List;
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
    String requestUrl = req.getRequestURI();
    try {
      viewArticle(requestUrl, req, resp);
    } catch (RedirectToValidUrlException e) {
      resp.sendRedirect(e.getUrl());
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      resp.sendRedirect("/404?url=" + requestUrl);
    }
  }

  private void viewArticle(String requestUrl, HttpServletRequest req,
                           HttpServletResponse resp) throws NumberFormatException,
        ArrayIndexOutOfBoundsException, RedirectToValidUrlException, IOException, ServletException {
    String id = StringUtils.split(requestUrl, "/")[1];
    Article article = getBusinessService().viewArticle(Long.parseLong(id), requestUrl);
    if (article == null) {
      resp.sendRedirect("/404?url=" + requestUrl);
    } else {
      req.setAttribute("article", article);
      List<Comment> comments = getBusinessService()
          .listComments(article.getId(), 0, Constant.LIMIT_COMMENTS_PER_PAGE);
      req.setAttribute("comments", comments);
      forwardToPage("article.jsp", req, resp);
    }
  }
}
