package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.adapters.output.persistence.projection.TimePlayedByCategoryProjection;
import com.ufrj.bd.steam.adapters.output.persistence.projection.UserProfileProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private String userName;
    private String userNickname;
    private String userProfileUrl;
    private String userOriginCountry;
    private String userAvatar;
    private Long userGamesCount;
    private Long userAchievementsCount;

    private List<TimePlayedByCategoryDTO> topCategories;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimePlayedByCategoryDTO {

        private String categoryName;
        private Long timesPlayed;
    }

    static public UserProfileDTO toUserProfileDTO(
            UserProfileProjection userProfile,
            List<TimePlayedByCategoryProjection> timePlayedByCategory
    ) {
        List<TimePlayedByCategoryDTO> topCategoriesDTO = timePlayedByCategory.stream()
                .map(category -> new TimePlayedByCategoryDTO(
                        category.getCategory(),
                        category.getTimesPlayed()))
                .collect(Collectors.toList());

        return new UserProfileDTO(
                userProfile.getUserName(),
                userProfile.getNickname(),
                userProfile.getProfileUrl(),
                userProfile.getOriginCountry(),
                userProfile.getAvatar(),
                userProfile.getObtainedGames(),
                userProfile.getObtainedAchievements(),
                topCategoriesDTO
        );
    }
}