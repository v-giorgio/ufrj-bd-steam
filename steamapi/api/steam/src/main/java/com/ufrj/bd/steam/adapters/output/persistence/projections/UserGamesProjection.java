package com.ufrj.bd.steam.adapters.output.persistence.projections;

import java.math.BigDecimal;

public interface UserGamesProjection {
    Long getId();
    String getGameName();
    BigDecimal getPlayedTime();
    Long getTotalAchievementsNumber();
    Long getObtainedAchievementsNumber();
    String getHeaderImage();
}
