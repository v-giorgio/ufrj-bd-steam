package com.ufrj.bd.steam.adapters.output.persistence.entities;

import com.ufrj.bd.steam.adapters.output.persistence.entities.keys.GameCategoryId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "JogoCategoria")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameCategoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GameCategoryId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "fk_Jogo_id", nullable = false)
    private GameEntity game;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "fk_Categoria_id", nullable = false)
    private CategoryEntity category;
}
