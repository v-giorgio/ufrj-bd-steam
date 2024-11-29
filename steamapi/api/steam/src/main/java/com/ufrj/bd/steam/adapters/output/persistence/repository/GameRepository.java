package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {


}
