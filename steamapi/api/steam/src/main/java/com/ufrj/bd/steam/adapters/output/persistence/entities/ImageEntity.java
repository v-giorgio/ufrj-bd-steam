package com.ufrj.bd.steam.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Imagem")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "capa")
    private Boolean isHeader;

    @ManyToOne
    @JoinColumn(name = "fk_Jogo_id", nullable = false)
    private GameEntity game;
}
