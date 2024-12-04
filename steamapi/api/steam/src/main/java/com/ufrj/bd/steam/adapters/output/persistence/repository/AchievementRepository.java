package com.ufrj.bd.steam.adapters.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufrj.bd.steam.adapters.output.persistence.entities.AchievementEntity;
import com.ufrj.bd.steam.adapters.output.persistence.projection.AchievementObtainedProjection;

public interface AchievementRepository extends JpaRepository<AchievementEntity, String>{

    @Query("SELECT DISTINCT A.id as name, " +
            "CASE WHEN UA.id IS NULL THEN 'false' ELSE 'true' END AS obtained, " +
            "A.obtainedPercentage as obtainedPercentage " +
            "FROM GameUserEntity GU " +
            "JOIN AchievementEntity A ON A.game.id = GU.game.id " +
            "LEFT JOIN UserAchievementEntity UA ON UA.achievement.id = A.id AND UA.user.id = GU.user.id " +
            "WHERE GU.user.id = :userId AND GU.game.id = :gameId")
    List<AchievementObtainedProjection> getAchievementObtainedByUserAndGame(Long userId, Long gameId);
}
