package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for handling error pages.
 */
@WebServlet("/error")
public class ErrorController extends AbstractController {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    forwardToPage("error.jsp", req, resp);
  }
}
