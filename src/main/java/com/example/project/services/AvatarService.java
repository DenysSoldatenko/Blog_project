package com.example.project.services;

import java.io.IOException;

/**
 * Service interface for managing user avatars.
 */
public interface AvatarService {

  int AVATAR_SIZE_IN_PX = 80;

  String MEDIA_AVATAR_PREFIX = "/media/avatar/";

  String downloadAvatar(String url) throws IOException;

  boolean deleteAvatarIfExists(String avatarPath);
}
