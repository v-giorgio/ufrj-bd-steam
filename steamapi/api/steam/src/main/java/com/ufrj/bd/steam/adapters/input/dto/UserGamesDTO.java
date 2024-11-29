package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.adapters.output.persistence.projection.UserGamesProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGamesDTO {

    private List<GameDTO> games;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameDTO {
        private String gameName;
        private BigDecimal playedTime;
        private Long totalAchievementsNumber;
        private Long obtainedAchievementsNumber;
        private String headerImage;
    }

    public static UserGamesDTO toUserGamesDTO(List<UserGamesProjection> userGames) {
        List<GameDTO> gameDTOs = userGames.stream()
                .map(game -> new GameDTO(
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
