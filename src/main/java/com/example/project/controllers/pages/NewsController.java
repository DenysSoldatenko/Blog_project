package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
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
    forwardToPage("news.jsp", req, resp);
  }
}
