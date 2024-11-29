package com.ufrj.bd.steam.adapters.input.controller;

import com.ufrj.bd.steam.adapters.input.dto.UserProfileDTO;
import com.ufrj.bd.steam.application.ports.input.GetAllUsersService;
import com.ufrj.bd.steam.application.ports.input.GetUserProfileService;
import com.ufrj.bd.steam.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserProfileService getUserProfileService;
    private final GetAllUsersService getAllUsersService;

    @Autowired
    public UserController(
            GetUserProfileService getUserProfileService,
            GetAllUsersService getAllUsersService
    ) {
        this.getUserProfileService = getUserProfileService;
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

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(getUserProfileService.execute(userId));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
