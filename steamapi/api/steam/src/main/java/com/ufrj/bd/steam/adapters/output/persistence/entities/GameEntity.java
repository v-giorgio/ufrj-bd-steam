package com.ufrj.bd.steam.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Jogo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "spec_recomendada")
    private String recommendedSpec;

    @Column(name = "genero")
    private String genre;

    @Column(name = "desenvolvedora")
    private String developerCompany;

    @Column(name = "editora")
    private String editorCompany;

    @Column(name = "classificacao_etaria")
    private Integer minimumAge;

    @Column(name = "data_lancamento")
    private LocalDate releaseDate;

    @Column(name = "gratuidade")
    private Boolean isFree;

    @Column(name = "descricao")
    private String description;

    @Column(name = "spec_minima")
    private String minimumSpec;

    @Column(name = "nome")
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameUserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AchievementEntity> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameCategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> videos = new ArrayList<>();
}
