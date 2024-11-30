package com.ufrj.bd.steam.adapters.input.controller;

import com.ufrj.bd.steam.adapters.input.dto.UserGamesDTO;
import com.ufrj.bd.steam.adapters.input.dto.UserProfileDTO;
import com.ufrj.bd.steam.adapters.input.dto.UsersListDTO;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesByUserService;
import com.ufrj.bd.steam.application.ports.input.GetAllUsersService;
import com.ufrj.bd.steam.application.ports.input.GetUserProfileService;
import com.ufrj.bd.steam.domain.exception.UserNotFoundException;
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

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserProfileService getUserProfileService;
    private final GetAllUsersService getAllUsersService;
    private final GetAllGamesByUserService getAllGamesByUserService;

    @Autowired
    public UserController(
            GetUserProfileService getUserProfileService,
            GetAllUsersService getAllUsersService,
            GetAllGamesByUserService getAllGamesByUserService
    ) {
        this.getUserProfileService = getUserProfileService;
        this.getAllUsersService = getAllUsersService;
        this.getAllGamesByUserService = getAllGamesByUserService;
    }

    @GetMapping
    @Operation(
            summary = "Obter todos os usuários",
            description = "Recupera todos os usuários registrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários recuperados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersListDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<UsersListDTO> getAll() {
        try {
            return ResponseEntity.ok(getAllUsersService.execute());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "Obter perfil de usuário por ID",
            description = "Recupera o perfil de um usuário específico através do ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil do usuário recuperado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<UserProfileDTO> getUserProfileById(
            @Parameter(description = "ID do usuário", required = true) @PathVariable Long userId
    ) {
        try {
            return ResponseEntity.ok(getUserProfileService.execute(userId));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}/games")
    @Operation(
            summary = "Obter jogos de um usuário por ID",
            description = "Recupera a lista de jogos de um usuário específico através do ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogos do usuário recuperados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserGamesDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<UserGamesDTO> getUserGames(
            @Parameter(description = "ID do usuário", required = true) @PathVariable Long userId
    ) {
        try {
            return ResponseEntity.ok(getAllGamesByUserService.execute(userId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
