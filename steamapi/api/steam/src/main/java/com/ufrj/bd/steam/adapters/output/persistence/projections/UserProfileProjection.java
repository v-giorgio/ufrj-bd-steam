package com.ufrj.bd.steam.adapters.output.persistence.projections;

public interface UserProfileProjection {
    Long getUserId();
    String getNickname();
    String getUserName();
    String getProfileUrl();
    String getOriginCountry();
    String getAvatar();
    Long getObtainedGames();
    Long getObtainedAchievements();
}
