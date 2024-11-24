package com.ufrj.bd.steam.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userName) {
        super(String.format("User with name %s not found", userName));
    }
}
