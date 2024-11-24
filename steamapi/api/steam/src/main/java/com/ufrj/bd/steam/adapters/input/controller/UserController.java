package com.ufrj.bd.steam.adapters.input.controller;

import com.ufrj.bd.steam.application.ports.input.GetAllUsersService;
import com.ufrj.bd.steam.application.ports.input.GetUserByNameService;
import com.ufrj.bd.steam.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserByNameService getUserByNameService;
    private final GetAllUsersService getAllUsersService;

    @Autowired
    public UserController(
            GetUserByNameService getUserByNameService,
            GetAllUsersService getAllUsersService
    ) {
        this.getUserByNameService = getUserByNameService;
        this.getAllUsersService = getAllUsersService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            return ResponseEntity.ok(getAllUsersService.execute());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(getUserByNameService.execute(name));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
