package com.ufrj.bd.steam.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Conquista")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AchievementEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "percentual_obtencao_usuarios")
    private BigDecimal obtainedPercentage;

    @ManyToOne
    @JoinColumn(name = "fk_Jogo_id", nullable = false)
    private GameEntity game;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAchievementEntity> users = new ArrayList<>();
}
