package com.ufrj.bd.steam.adapters.output.persistence.projections;

public interface GameBasicDetailsProjection {
    Long getId();
    String getGameName();
    String getHeaderImageUrl();
}
