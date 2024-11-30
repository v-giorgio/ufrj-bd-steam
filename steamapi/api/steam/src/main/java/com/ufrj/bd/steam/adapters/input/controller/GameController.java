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

import com.ufrj.bd.steam.adapters.input.dto.GamesListDTO;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GetAllGamesService getAllGamesService;
    private final GetGamesAndCategoriesService getGamesAndCategoriesService;

    @Autowired
    public GameController(GetAllGamesService getAllGamesService, GetGamesAndCategoriesService getGamesAndCategoriesService) {
        this.getAllGamesService = getAllGamesService;
        this.getGamesAndCategoriesService = getGamesAndCategoriesService;
    }

    @GetMapping
    @Operation(
            summary = "Obter lista de todos os jogos",
            description = "Recupera uma lista de todos os jogos disponíveis no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogos recuperados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GamesListDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<GamesListDTO> getAll() {
        try {
            return ResponseEntity.ok(getAllGamesService.execute());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/details/{gameId}")
    @Operation(
            summary = "Detalhes de um jogo",
            description = "Obter detalhes do jogo desejado, junto com uma lista de suas categorias"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo e categorias recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<GameCategoriesDTO>> getByGameId(@PathVariable Long gameId) {
        try {
            return ResponseEntity.ok(getGamesAndCategoriesService.execute(gameId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
