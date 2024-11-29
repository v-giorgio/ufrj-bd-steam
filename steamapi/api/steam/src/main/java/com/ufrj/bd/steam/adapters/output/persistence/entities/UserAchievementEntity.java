package com.ufrj.bd.steam.adapters.output.persistence.entities;

import com.ufrj.bd.steam.adapters.output.persistence.entities.keys.UserAchievementId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "UsuarioConquista")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAchievementEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserAchievementId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "fk_Usuario_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @MapsId("achievementId")
    @JoinColumn(name = "fk_Conquista_id", nullable = false)
    private AchievementEntity achievement;
}
