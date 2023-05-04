package com.example.project.controllers;

import com.example.project.dao.form.AbstractForm;
import com.example.project.services.BusinessService;
import com.example.project.services.impl.ServiceManager;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base controller providing common functionality for controllers.
 */
@Getter
public abstract class AbstractController extends HttpServlet {
  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private BusinessService businessService;

  /**
   * Calculates the pagination offset based on the current page number and limit.
   *
   * @param req   the HTTP servlet request containing the page parameter
   * @param limit the maximum number of items per page
   * @return the calculated offset for pagination
   * @throws NumberFormatException if the page parameter is not a valid integer
   */
  public final int getOffset(HttpServletRequest req, int limit) {
    String val = req.getParameter("page");
    if (val != null) {
      int page = Integer.parseInt(val);
      return (page - 1) * limit;
    } else {
      return 0;
    }
  }

  @Override
  public void init() {
    businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
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

  /**
   * Creates a form object from the HttpServletRequest parameters.
   *
   * @param req The HttpServletRequest object
   * @param formClass The class of the form object to create
   * @param <T> The type of the form object
   * @return The created form object
   * @throws ServletException if a servlet-specific error occurs
   */
  public final <T extends AbstractForm> T createForm(HttpServletRequest req,
                                                     Class<T> formClass) throws ServletException {
    try {
      Constructor<T> constructor = formClass.getConstructor();
      T form = constructor.newInstance();
      form.setLocale(req.getLocale());
      BeanUtils.populate(form, req.getParameterMap());
      return form;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new ServletException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}