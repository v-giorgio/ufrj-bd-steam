package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.domain.models.User;

public interface GetUserByNameService {

    User execute(String name);
}
