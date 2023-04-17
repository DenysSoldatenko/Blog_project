package com.example.project.listeners;


import com.example.project.services.impl.ServiceManager;
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
    ServiceManager.getInstance(sce.getServletContext());
    LOGGER.info("Application started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServiceManager.getInstance(sce.getServletContext()).destroy();
    LOGGER.info("Application destroyed");
  }
}
