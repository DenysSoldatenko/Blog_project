package com.example.project.listeners;

import com.example.project.entities.Category;
import com.example.project.services.impl.ServiceManager;
import com.example.project.utils.Constant;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application listener for initializing and destroying application resources.
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServiceManager serviceManager = ServiceManager.getInstance(sce.getServletContext());
    Map<Integer, Category> map = serviceManager.getBusinessService().mapCategories();
    sce.getServletContext().setAttribute(Constant.CATEGORY_MAP, map);
    System.out.println(Constant.CATEGORY_MAP + map.toString());
    LOGGER.info("Application started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServiceManager.getInstance(sce.getServletContext()).destroy();
    LOGGER.info("Application destroyed");
  }
}
