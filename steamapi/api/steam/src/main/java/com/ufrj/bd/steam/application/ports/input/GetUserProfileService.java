package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.adapters.input.dto.UserProfileDTO;

public interface GetUserProfileService {

    UserProfileDTO execute(Long userId);
}
