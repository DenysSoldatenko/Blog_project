package com.example.project.services.impl;

import com.example.project.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DemoNotificationService implements NotificationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoNotificationService.class);

  @Override
  public void sendNotification(String title, String content) {
    LOGGER.info("New comment: title=" + title + ", content=" + content);
  }

  @Override
  public void shutdown() {
    // do nothing
  }
}
