package com.ufrj.bd.steam.adapters.output.persistence.projection;

import java.math.BigDecimal;

public interface AchievementObtainedProjection {
    String getName();
    Boolean getObtained();
    BigDecimal getObtainedPercentage();
}
