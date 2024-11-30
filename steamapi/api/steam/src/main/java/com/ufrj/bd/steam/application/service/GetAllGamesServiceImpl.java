package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.input.dto.GamesListDTO;
import com.ufrj.bd.steam.adapters.output.persistence.repository.GameRepository;
import com.ufrj.bd.steam.application.ports.input.GetAllGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllGamesServiceImpl implements GetAllGamesService {

    private final GameRepository gameRepository;

    @Autowired
    public GetAllGamesServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GamesListDTO execute() {
        return GamesListDTO.toGamesListDTO(gameRepository.findAllGames());
    }
}
