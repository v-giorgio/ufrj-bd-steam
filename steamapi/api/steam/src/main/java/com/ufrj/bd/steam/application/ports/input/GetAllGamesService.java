package com.ufrj.bd.steam.application.ports.input;


import com.ufrj.bd.steam.adapters.input.dto.GamesListDTO;

public interface GetAllGamesService {
    GamesListDTO execute();
}
