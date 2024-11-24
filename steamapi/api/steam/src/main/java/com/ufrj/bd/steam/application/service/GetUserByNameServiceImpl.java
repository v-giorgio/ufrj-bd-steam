package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.output.persistence.repository.UserRepository;
import com.ufrj.bd.steam.application.ports.input.GetUserByNameService;
import com.ufrj.bd.steam.domain.exception.UserNotFoundException;
import com.ufrj.bd.steam.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByNameServiceImpl implements GetUserByNameService {

    private final UserRepository userRepository;

    @Autowired
    public GetUserByNameServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name))
                .toDomain();
    }
}
