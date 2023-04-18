package com.example.project.services.impl;

import com.example.project.services.BusinessService;
import com.example.project.utils.AppUtil;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletContext;
import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages services and resources of the application.
 */
public class ServiceManager {
  private static final String SERVICE_MANAGER = "SERVICE_MANAGER";

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);

  final Properties applicationProperties = new Properties();

  final BasicDataSource dataSource;

  @Getter
  final BusinessService businessService;

  /**
   * Returns the singleton instance of the ServiceManager.
   *
   * @param context the servlet context
   * @return the ServiceManager instance
   */
  public static ServiceManager getInstance(ServletContext context) {
    ServiceManager instance = (ServiceManager) context.getAttribute(SERVICE_MANAGER);
    if (instance == null) {
      instance = new ServiceManager();
      context.setAttribute(SERVICE_MANAGER, instance);
    }
    return instance;
  }

  private ServiceManager() {
    AppUtil.loadProperties(applicationProperties, "application.properties");
    dataSource = createBasicDataSource();
    businessService = new BusinessServiceImpl(this);
    LOGGER.info("ServiceManager instance created");
  }

  public String getApplicationProperty(String property) {
    return applicationProperties.getProperty(property);
  }

  /**
   * Destroys the ServiceManager instance and releases resources.
   */
  public void destroy() {
    try {
      dataSource.close();
    } catch (SQLException e) {
      LOGGER.error("Close dataSource failed: " + e.getMessage(), e);
    }

    LOGGER.info("ServiceManager instance destroyed");
  }

  private BasicDataSource createBasicDataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDefaultAutoCommit(false);
    ds.setRollbackOnReturn(true);
    ds.setDriverClassName(getApplicationProperty("db.driver"));
    ds.setUrl(getApplicationProperty("db.url"));
    ds.setUsername(getApplicationProperty("db.username"));
    ds.setPassword(getApplicationProperty("db.password"));
    ds.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
    ds.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
    return ds;
  }
}