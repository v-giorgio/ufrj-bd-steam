package com.ufrj.bd.steam.adapters.output.persistence.entities;

import com.ufrj.bd.steam.adapters.output.persistence.entities.keys.GameUserId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "JogoUsuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameUserEntity {

    @EmbeddedId
    private GameUserId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "fk_Usuario_id")
    private UserEntity user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "fk_Jogo_id")
    private GameEntity game;

    @Column(name = "tempo_jogado")
    private BigDecimal playtime;
}
