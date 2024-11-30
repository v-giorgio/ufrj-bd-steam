package com.ufrj.bd.steam.adapters.input.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrj.bd.steam.adapters.input.dto.GameCategoriesDTO;
import com.ufrj.bd.steam.application.ports.input.GetGamesAndCategoriesService;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GetGamesAndCategoriesService service;

    @Autowired
    public GameController(GetGamesAndCategoriesService service) {
        this.service = service;
    }

    @GetMapping("/details/{gameId}")
    public ResponseEntity<List<GameCategoriesDTO>> getByGameId(@PathVariable Long gameId) {
        try {
            return ResponseEntity.ok(service.execute(gameId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
