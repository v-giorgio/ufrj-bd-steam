package com.ufrj.bd.steam.adapters.output.persistence.entities.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAchievementId implements Serializable {
    private Long userId;
    private String achievementId;
}
