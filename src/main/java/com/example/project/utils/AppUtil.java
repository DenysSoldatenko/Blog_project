package com.example.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading properties from the classpath.
 */
public class AppUtil {

  /**
   * Loads properties from the classpath.
   *
   * @param props         the Properties object to load properties into
   * @param classPathUrl  the classpath URL of the properties file
   * @throws IllegalArgumentException if properties cannot be loaded from the classpath
   */
  public static void loadProperties(Properties props, String classPathUrl) {
    try (InputStream in = AppUtil.class.getClassLoader().getResourceAsStream(classPathUrl)) {
      props.load(in);
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't load properties from classpath:" + classPathUrl, e);
    }
  }
}
