package com.example.project.controllers.ajax;


import com.example.project.controllers.AbstractController;
import com.example.project.dao.form.CommentForm;
import com.example.project.entities.Comment;
import com.example.project.exceptions.ApplicationException;
import com.example.project.exceptions.ValidateException;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for submitting a new comment via AJAX.
 */
@WebServlet("/ajax/comment")
public class NewCommentController extends AbstractController {

  @Override
  protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
    try {
      CommentForm form = createForm(req, CommentForm.class);
      Comment comment = getBusinessService().createComment(form);
      req.setAttribute("comments", Collections.singleton(comment));
      forwardToFragment("comments.jsp", req, resp);
    } catch (ValidateException e) {
      throw new ApplicationException("Invalid create comment try: " + e.getMessage(), e);
    }
  }
}
