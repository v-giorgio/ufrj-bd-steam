package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersListDTO {

    private List<UserDTO> users;

    @Data
    @AllArgsConstructor
    public static class UserDTO {
        private Long id;
        private String nickname;
        private String name;
        private String profileUrl;
        private String originCountry;
        private String avatar;
    }

    public static UsersListDTO fromDomain(List<User> users) {
        List<UserDTO> userDTOs = users.stream()
                .map(u -> new UserDTO(
                        u.getId(),
                        u.getNickname(),
                        u.getName(),
                        u.getProfileUrl(),
                        u.getOriginCountry(),
                        u.getAvatar()

                ))
                .toList();

        return new UsersListDTO(userDTOs);
    }
}
