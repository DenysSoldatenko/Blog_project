package com.example.blog_project.controller.page;

import com.example.blog_project.controller.AbstractController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for handling requests related to the "Contact" page.
 */
@WebServlet("/contact")
public class ContactController extends AbstractController {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    forwardToPage("contact.jsp", req, resp);
  }
}
