package com.ufrj.bd.steam.application.ports.input;

import java.util.List;

import com.ufrj.bd.steam.adapters.input.dto.GameCategoriesDTO;

public interface GetGamesAndCategoriesService {

    List<GameCategoriesDTO> execute(Long gameId);
}
