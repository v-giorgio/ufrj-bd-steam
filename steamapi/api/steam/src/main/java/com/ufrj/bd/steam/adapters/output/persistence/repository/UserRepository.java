package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.UserEntity;
import com.ufrj.bd.steam.adapters.output.persistence.projections.UserProfileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT " +
            "U.id AS userId, " +
            "U.nickname AS nickname, " +
            "U.name AS userName, " +
            "U.profileUrl AS profileUrl, " +
            "U.originCountry AS originCountry, " +
            "U.avatar AS avatar, " +
            "COUNT(DISTINCT JU.game.id) AS obtainedGames, " +
            "COUNT(DISTINCT UA.achievement.id) AS obtainedAchievements " +
            "FROM UserEntity U " +
            "LEFT JOIN GameUserEntity JU ON U.id = JU.user.id " +
            "LEFT JOIN UserAchievementEntity UA ON U.id = UA.user.id " +
            "WHERE U.id = :userId " +
            "GROUP BY U.id " +
            "ORDER BY obtainedGames DESC, obtainedAchievements DESC")
    Optional<UserProfileProjection> findUserProfileByUserId(Long userId);

}
