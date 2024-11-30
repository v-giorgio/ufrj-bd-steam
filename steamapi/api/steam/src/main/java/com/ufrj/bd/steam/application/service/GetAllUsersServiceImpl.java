package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.input.dto.UsersListDTO;
import com.ufrj.bd.steam.adapters.output.persistence.entities.UserEntity;
import com.ufrj.bd.steam.adapters.output.persistence.repository.UserRepository;
import com.ufrj.bd.steam.application.ports.input.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllUsersServiceImpl implements GetAllUsersService {

    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersListDTO execute() {
        return UsersListDTO.fromDomain(userRepository.findAll().stream().map(UserEntity::toDomain).toList());
    }
}
