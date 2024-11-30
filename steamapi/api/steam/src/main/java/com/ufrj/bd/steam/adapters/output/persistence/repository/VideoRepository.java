package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {

    List<VideoEntity> findByGameId(Long gameId);
}
