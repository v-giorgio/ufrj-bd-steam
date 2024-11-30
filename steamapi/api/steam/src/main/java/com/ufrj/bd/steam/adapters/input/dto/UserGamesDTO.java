package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.adapters.output.persistence.projections.UserGamesProjection;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class UserGamesDTO {

    private List<GameDTO> games;

    @Data
    @AllArgsConstructor
    public static class GameDTO {
        private Long id;
        private String gameName;
        private BigDecimal playedTime;
        private Long totalAchievementsNumber;
        private Long obtainedAchievementsNumber;
        private String headerImage;
    }

    public static UserGamesDTO toUserGamesDTO(List<UserGamesProjection> userGames) {
        List<GameDTO> gameDTOs = userGames.stream()
                .map(game -> new GameDTO(
                        game.getId(),
                        game.getGameName(),
                        game.getPlayedTime(),
                        game.getTotalAchievementsNumber(),
                        game.getObtainedAchievementsNumber(),
                        game.getHeaderImage()
                ))
                .toList();

        return new UserGamesDTO(gameDTOs);
    }
}
