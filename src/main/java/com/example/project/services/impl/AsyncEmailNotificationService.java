package com.example.project.services.impl;

import com.example.project.models.AbstractModel;
import com.example.project.services.NotificationService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An asynchronous implementation of the NotificationService interface
 * for sending email notifications.
 */
public class AsyncEmailNotificationService implements NotificationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncEmailNotificationService.class);
  private final ServiceManager serviceManager;
  private final ExecutorService executorService;
  private final int tryCount;

  AsyncEmailNotificationService(ServiceManager serviceManager) {
    super();
    this.serviceManager = serviceManager;
    this.executorService = Executors.newCachedThreadPool();
    this.tryCount = Integer.parseInt(serviceManager.getApplicationProperty("email.sendTryCount"));
  }

  @Override
  public void sendNotification(String title, String content) {
    executorService.submit(new EmailItem(title, content, tryCount));
  }

  @Override
  public void shutdown() {
    executorService.shutdown();
  }


  private class EmailItem extends AbstractModel implements Runnable {
    private final String subject;
    private final String content;
    private int tryCount;

    private EmailItem(String subject, String content, int tryCount) {
      super();
      this.subject = subject;
      this.content = content;
      this.tryCount = tryCount;
    }

    private boolean isValidTryCount() {
      return tryCount > 0;
    }

    @Override
    public void run() {
      try {
        String notificationEmail = serviceManager.getApplicationProperty("email.notificationEmail");
        SimpleEmail email = new SimpleEmail();
        email.setCharset("utf-8");
        email.setHostName(serviceManager.getApplicationProperty("email.smtp.server"));
        email.setSSLOnConnect(true);
        email.setSslSmtpPort(serviceManager.getApplicationProperty("email.smtp.port"));
        email.setAuthenticator(
            new DefaultAuthenticator(
            serviceManager.getApplicationProperty("email.smtp.username"),
            serviceManager.getApplicationProperty("email.smtp.password")
            )
        );
        email.setFrom(serviceManager.getApplicationProperty("email.fromEmail"));
        email.setSubject(subject);
        email.setMsg(content);
        email.addTo(notificationEmail);
        email.send();
      } catch (EmailException e) {
        LOGGER.error("Can't send email: " + e.getMessage(), e);
        tryCount--;
        if (isValidTryCount()) {
          LOGGER.info("Resend email: {}", this);
          executorService.submit(this);
        } else {
          LOGGER.error("Email was not sent: limit of try count");
        }
      } catch (Exception e) {
        LOGGER.error("Error during send email: " + e.getMessage(), e);
      }
    }
  }
}
