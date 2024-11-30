package com.ufrj.bd.steam.adapters.input.controller;

import com.ufrj.bd.steam.adapters.input.dto.GamesListDTO;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
}
