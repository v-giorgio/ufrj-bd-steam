package com.ufrj.bd.steam.domain.models;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String name;
    private String profileUrl;
    private String originCountry;
    private String avatar;
}
