package com.ufrj.bd.steam.adapters.output.persistence.entities.keys;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GameUserId implements Serializable {
    private Long userId;
    private Long gameId;
}
