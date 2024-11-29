package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.adapters.input.dto.AchievementObtainedDTO;

import java.util.List;

public interface GetAchievementByUserAndGameService {

    List<AchievementObtainedDTO> execute(Long userId, Long gameId);
}
