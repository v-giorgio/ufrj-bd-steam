package com.ufrj.bd.steam.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ufrj.bd.steam.adapters.input.dto.GameCategoriesDTO;
import com.ufrj.bd.steam.adapters.output.persistence.repository.GameRepository;
import com.ufrj.bd.steam.application.ports.input.GetGamesAndCategoriesService;

@Service
public class GetGamesAndCategoriesServiceImpl implements GetGamesAndCategoriesService {
    private final GameRepository gameRepository;

    public GetGamesAndCategoriesServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameCategoriesDTO> execute(Long gameId) {
        return gameRepository.findGamesAndCategories(gameId).stream().map(GameCategoriesDTO::new).toList();
    }
}
