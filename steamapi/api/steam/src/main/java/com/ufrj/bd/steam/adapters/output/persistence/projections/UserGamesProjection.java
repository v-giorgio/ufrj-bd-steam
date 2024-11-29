package com.ufrj.bd.steam.adapters.output.persistence.projections;

import java.math.BigDecimal;

public interface UserGamesProjection {
    String getGameName();
    BigDecimal getPlayedTime();
    Long getTotalAchievementsNumber();
    Long getObtainedAchievementsNumber();
    String getHeaderImage();
}
