package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.adapters.input.dto.UserGamesDTO;

public interface GetAllGamesByUserService {

    UserGamesDTO execute(Long userId);
}
