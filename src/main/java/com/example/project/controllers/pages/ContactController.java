package com.example.project.controllers.pages;

import com.example.project.controllers.AbstractController;
import com.example.project.dao.form.ContactForm;
import com.example.project.exceptions.ApplicationException;
import com.example.project.exceptions.ValidateException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for handling requests related to the "Contact" page.
 */
@WebServlet("/contact")
public class ContactController extends AbstractController {
  private static final String CONTACT_REQUEST_SUCCESS = "CONTACT_REQUEST_SUCCESS";

  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    Boolean isSuccess = (Boolean) req.getSession().getAttribute(CONTACT_REQUEST_SUCCESS);
    if (isSuccess == null) {
      isSuccess = Boolean.FALSE;
    } else {
      req.getSession().removeAttribute(CONTACT_REQUEST_SUCCESS);
    }
    req.setAttribute("success", isSuccess);
    forwardToPage("contact.jsp", req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
    try {
      ContactForm form = createForm(req, ContactForm.class);
      getBusinessService().createContactRequest(form);
      req.getSession().setAttribute(CONTACT_REQUEST_SUCCESS, Boolean.TRUE);
      resp.sendRedirect("/contact");
    } catch (ValidateException e) {
      throw new ApplicationException("Validation should be done on client side: "
          + e.getMessage(), e);
    }
  }
}
