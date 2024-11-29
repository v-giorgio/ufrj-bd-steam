package com.ufrj.bd.steam.adapters.output.persistence.projections;

public interface TimePlayedByCategoryProjection {
    String getCategory();
    Long getTimesPlayed();
}
