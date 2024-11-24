package com.ufrj.bd.steam.application.ports.input;

import com.ufrj.bd.steam.domain.models.User;

import java.util.List;

public interface GetAllUsersService {

    List<User> execute();
}
