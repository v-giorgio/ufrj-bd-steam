package com.ufrj.bd.steam.application.ports.input;


import com.ufrj.bd.steam.adapters.input.dto.GameMediaDTO;

public interface GetGameMediaService {
    GameMediaDTO execute(Long gameId);
}
