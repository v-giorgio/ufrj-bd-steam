package com.ufrj.bd.steam.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Game {
    private Long id;
    private String recommendedSpec;
    private String genre;
    private String developerCompany;
    private String editorCompany;
    private Integer minimumAge;
    private LocalDate releaseDate;
    private Boolean isFree;
    private String description;
    private String minimumSpec;
    private String name;
}
