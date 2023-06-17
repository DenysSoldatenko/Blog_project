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
  private static final String DISPLAY_INFO_MESSAGE = "DISPLAY_INFO_MESSAGE";

  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    Boolean displayInfoMessage = shouldInfoMessageBeDisplayed(req);
    req.setAttribute("displayInfoMessage", displayInfoMessage);
    forwardToPage("contact.jsp", req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
    try {
      ContactForm form = createForm(req, ContactForm.class);
      getBusinessService().createContactRequest(form);
      req.getSession().setAttribute(DISPLAY_INFO_MESSAGE, Boolean.TRUE);
      resp.sendRedirect("/contact");
    } catch (ValidateException e) {
      throw new ApplicationException("Validation should be done on client side: "
          + e.getMessage(), e);
    }
  }

  private boolean shouldInfoMessageBeDisplayed(HttpServletRequest req) {
    Boolean displayInfoMessage = (Boolean) req.getSession().getAttribute(DISPLAY_INFO_MESSAGE);
    if (displayInfoMessage == null) {
      displayInfoMessage = Boolean.FALSE;
    } else {
      req.getSession().removeAttribute(DISPLAY_INFO_MESSAGE);
    }
    return displayInfoMessage;
  }
}

