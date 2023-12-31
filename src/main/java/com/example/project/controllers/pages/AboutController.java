package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for handling requests related to the "About" page.
 */
@WebServlet("/about")
public class AboutController extends AbstractController {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    forwardToPage("about.jsp", req, resp);
  }
}
