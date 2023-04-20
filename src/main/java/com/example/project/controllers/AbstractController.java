package com.example.project.controllers;

import com.example.project.services.BusinessService;
import com.example.project.services.impl.ServiceManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base controller providing common functionality for controllers.
 */
public abstract class AbstractController extends HttpServlet {
  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private BusinessService businessService;

  @Override
  public void init() {
    businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
  }

  public final BusinessService getBusinessService() {
    return businessService;
  }

  public final void forwardToPage(String jspPage, HttpServletRequest req,
                                  HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("currentPage", "page/" + jspPage);
    req.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(req, resp);
  }

  public final void forwardToFragment(String jspPage, HttpServletRequest req,
                                    HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspPage).forward(req, resp);
  }
}
