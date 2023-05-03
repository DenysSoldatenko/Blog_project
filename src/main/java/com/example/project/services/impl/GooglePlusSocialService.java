package com.example.project.services.impl;

import com.example.project.exceptions.ApplicationException;
import com.example.project.models.SocialAccount;
import com.example.project.services.SocialService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GooglePlusSocialService implements SocialService {
  private final String googlePlusClientId;
  private final List<String> issuers;

  GooglePlusSocialService(ServiceManager serviceManager) {
    this.googlePlusClientId = serviceManager.getApplicationProperty("social.googleplus.clientId");
    this.issuers = Arrays.asList("https://accounts.google.com", "accounts.google.com");
  }

  @Override
  public SocialAccount getSocialAccount(String authToken) {
    try {
      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
          .Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
          .setAudience(Collections.singleton(googlePlusClientId))
          .setIssuers(issuers).build();
      GoogleIdToken idToken = verifier.verify(authToken);
      if (idToken != null) {
        Payload payload = idToken.getPayload();
        return new SocialAccount(payload.getEmail(),
        (String) payload.get("given_name"),
        (String) payload.get("picture"));
      } else {
        throw new ApplicationException("Can't get account by authToken: " + authToken);
      }
    } catch (GeneralSecurityException | IOException e) {
      throw new ApplicationException(e);
    }
  }
}
