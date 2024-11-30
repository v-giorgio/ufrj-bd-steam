package com.ufrj.bd.steam.adapters.input.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Obter as conquistas de um usuário para um jogo",
            description = "Traz todas as conquistas de um usuário para um jogo, tanto as obtidas quanto as não obtidas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquistas obtidas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementObtainedDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<AchievementObtainedDTO>> getByUserAndGame(
            @Parameter(description = "ID do usuário", required = true) @PathVariable Long userId,
            @Parameter(description = "ID do jogo", required = true) @PathVariable Long gameId
    ) {
        try {
            return ResponseEntity.ok(getAchievementByUserAndGameService.execute(userId, gameId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
