package com.example.project.services;

/**
 * Service interface for sending notifications and managing notification service.
 */
public interface NotificationService {

  void sendNotification(String title, String content);

  void shutdown();
}
