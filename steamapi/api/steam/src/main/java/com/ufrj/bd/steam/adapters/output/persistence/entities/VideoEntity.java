package com.ufrj.bd.steam.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Video")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_Jogo_id")
    private GameEntity game;
}
