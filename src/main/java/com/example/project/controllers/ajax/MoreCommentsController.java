package com.example.project.controllers.ajax;

import com.example.project.controllers.AbstractController;
import com.example.project.entities.Comment;
import com.example.project.utils.Constant;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for retrieving additional comments via AJAX.
 */
@WebServlet("/ajax/comments")
public class MoreCommentsController extends AbstractController {

  private int getOffset(HttpServletRequest req) {
    String val = req.getParameter("offset");
    if (val != null) {
      return Integer.parseInt(val);
    } else {
      return 0;
    }
  }

  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    int offset = getOffset(req);
    long idArticle = Long.parseLong(req.getParameter("idArticle"));
    List<Comment> comments = getBusinessService().listComments(idArticle,
        offset, Constant.LIMIT_COMMENTS_PER_PAGE);
    req.setAttribute("comments", comments);
    forwardToFragment("comments.jsp", req, resp);
  }
}
