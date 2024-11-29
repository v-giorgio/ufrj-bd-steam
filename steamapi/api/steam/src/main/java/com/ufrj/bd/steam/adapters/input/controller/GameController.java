package com.ufrj.bd.steam.adapters.input.controller;

import com.ufrj.bd.steam.adapters.input.dto.GamesListDTO;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GetAllGamesService getAllGamesService;

    @Autowired
    public GameController(GetAllGamesService getAllGamesService) {
        this.getAllGamesService = getAllGamesService;
    }

    @GetMapping
    public ResponseEntity<GamesListDTO> getAll() {
        try {
            return ResponseEntity.ok(getAllGamesService.execute());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
