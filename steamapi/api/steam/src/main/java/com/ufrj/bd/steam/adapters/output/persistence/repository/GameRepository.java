package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.GameEntity;
import com.ufrj.bd.steam.adapters.output.persistence.projection.UserGamesProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ufrj.bd.steam.adapters.output.persistence.projection.GameAndCategoriesProjection;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Query("SELECT " +
            "J.name AS gameName, " +
            "JU.playtime AS playedTime, " +
            "(SELECT COUNT(C.id) " +
            " FROM AchievementEntity C " +
            " WHERE C.game.id = J.id) AS totalAchievementsNumber, " +
            "(SELECT COUNT(UC.achievement.id) " +
            " FROM UserAchievementEntity UC " +
            " WHERE UC.user.id = JU.user.id " +
            "   AND UC.achievement.game.id = J.id) AS obtainedAchievementsNumber, " +
            "(SELECT I.url " +
            " FROM ImageEntity I " +
            " WHERE I.game.id = J.id " +
            "   AND I.isHeader = TRUE) AS headerImage " +
            "FROM GameUserEntity JU " +
            "JOIN JU.game J " +
            "WHERE JU.user.id = :userId " +
            "ORDER BY playedTime DESC, obtainedAchievementsNumber DESC")
    List<UserGamesProjection> findGamesByUser(Long userId);

    @Query(value = "SELECT J.id as id, " + 
    "J.spec_recomendada as recommendedSpec, " + 
    "J.genero as genre, " + 
    "J.desenvolvedora as developerCompany, " + 
    "J.editora as editorCompany, " + 
    "J.descricao as description, " + 
    "J.nome as name, " + 
    "J.spec_minima as minimumSpec, " + 
    "group_concat(c.nome separator ', ') as categories FROM Jogo J LEFT JOIN JogoCategoria jc ON J.id = jc.fk_Jogo_id LEFT JOIN Categoria c on c.id = jc.fk_Categoria_id WHERE J.id = :gameId", nativeQuery=true)
    List<GameAndCategoriesProjection> findGamesAndCategories(Long gameId);
}


