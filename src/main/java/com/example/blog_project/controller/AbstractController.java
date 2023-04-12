package com.example.blog_project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {
  public final void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("currentPage", "page/" + jspPage);
    req.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(req, resp);
  }

  public final void forwardToFragment(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspPage).forward(req, resp);
  }
}
