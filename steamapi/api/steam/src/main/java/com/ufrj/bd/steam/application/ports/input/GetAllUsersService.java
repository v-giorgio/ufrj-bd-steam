package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.adapters.input.dto.UsersListDTO;

public interface GetAllUsersService {

    UsersListDTO execute();
}
