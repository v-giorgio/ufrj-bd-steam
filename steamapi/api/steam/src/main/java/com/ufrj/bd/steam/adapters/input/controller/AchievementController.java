package com.ufrj.bd.steam.adapters.input.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrj.bd.steam.adapters.input.dto.AchievementObtainedDTO;
import com.ufrj.bd.steam.application.ports.input.GetAchievementByUserAndGameService;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
    private final GetAchievementByUserAndGameService getAchievementByUserAndGameService;

    @Autowired
    public AchievementController(GetAchievementByUserAndGameService getAchievementByUserAndGameService) {
        this.getAchievementByUserAndGameService = getAchievementByUserAndGameService;
    }

    @GetMapping("/user/{userId}/game/{gameId}")
    public ResponseEntity<List<AchievementObtainedDTO>> getByUserAndGame(@PathVariable Long userId, @PathVariable Long gameId) {
        try {
            return ResponseEntity.ok(getAchievementByUserAndGameService.execute(userId, gameId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
