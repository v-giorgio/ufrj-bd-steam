package com.ufrj.bd.steam.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ufrj.bd.steam.adapters.input.dto.AchievementObtainedDTO;
import com.ufrj.bd.steam.adapters.output.persistence.repository.AchievementRepository;
import com.ufrj.bd.steam.application.ports.input.GetAchievementByUserAndGameService;

@Service
public class GetAchievementByUserAndGameServiceImpl implements GetAchievementByUserAndGameService {
    private final AchievementRepository achievementRepository;

    public GetAchievementByUserAndGameServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<AchievementObtainedDTO> execute(Long userId, Long gameId) {
        return achievementRepository.getAchievementObtainedByUserAndGame(userId, gameId).stream().map(AchievementObtainedDTO::new).toList();
    }
}
