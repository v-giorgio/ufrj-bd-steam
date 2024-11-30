package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.adapters.output.persistence.projections.GameBasicDetailsProjection;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class GamesListDTO {

    private List<GameDTO> games;

    @Data
    @AllArgsConstructor
    public static class GameDTO {
        private Long id;
        private String name;
        private String headerImageUrl;
    }

    public static GamesListDTO toGamesListDTO(List<GameBasicDetailsProjection> gamesList) {
        List<GameDTO> gameDTOs = gamesList.stream()
                .map(game -> new GamesListDTO.GameDTO(
                        game.getId(),
                        game.getGameName(),
                        game.getHeaderImageUrl()
                ))
                .toList();

        return new GamesListDTO(gameDTOs);
    }
}
