package com.example.project.services;

import com.example.project.models.SocialAccount;

/**
 * Service interface for interacting with social media accounts.
 */
public interface SocialService {

  SocialAccount getSocialAccount(String authToken);
}
