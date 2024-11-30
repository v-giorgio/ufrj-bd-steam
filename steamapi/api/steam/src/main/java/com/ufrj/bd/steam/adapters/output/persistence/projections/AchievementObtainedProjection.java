package com.ufrj.bd.steam.adapters.output.persistence.projections;

import java.math.BigDecimal;

public interface AchievementObtainedProjection {
    String getName();
    Boolean getObtained();
    BigDecimal getObtainedPercentage();
}
