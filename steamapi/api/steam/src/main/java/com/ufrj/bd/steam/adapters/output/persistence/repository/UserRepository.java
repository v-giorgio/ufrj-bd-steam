package com.ufrj.bd.steam.adapters.output.persistence.repository;

import com.ufrj.bd.steam.adapters.output.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

}
