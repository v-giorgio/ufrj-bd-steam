package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    List<ImageEntity> findByGameId(Long gameId);
}
