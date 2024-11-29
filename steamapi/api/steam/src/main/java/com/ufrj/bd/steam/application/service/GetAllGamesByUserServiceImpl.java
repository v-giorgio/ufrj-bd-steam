package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.input.dto.UserGamesDTO;
import com.ufrj.bd.steam.adapters.output.persistence.repository.GameRepository;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesByUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllGamesByUserServiceImpl implements GetAllGamesByUserService {

    private final GameRepository gameRepository;

    @Autowired
    public GetAllGamesByUserServiceImpl(
            GameRepository gameRepository
    ) {
        this.gameRepository = gameRepository;
    }

    @Override
    public UserGamesDTO execute(Long userId) {
        return UserGamesDTO.toUserGamesDTO(gameRepository.findGamesByUser(userId));
    }
}
