package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.CategoryEntity;
import com.ufrj.bd.steam.adapters.output.persistence.projections.TimePlayedByCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT C.name AS category, COUNT(*) AS timesPlayed " +
            "FROM UserEntity U " +
            "JOIN GameUserEntity JU ON U.id = JU.user.id " +
            "JOIN GameEntity G ON JU.game.id = G.id " +
            "JOIN GameCategoryEntity GC ON G.id = GC.game.id " +
            "JOIN CategoryEntity C ON GC.category.id = C.id " +
            "WHERE U.id = :userId " +
            "GROUP BY C.name " +
            "ORDER BY COUNT(*) DESC")
    List<TimePlayedByCategoryProjection> findTopCategoriesByUserId(Long userId);
}
