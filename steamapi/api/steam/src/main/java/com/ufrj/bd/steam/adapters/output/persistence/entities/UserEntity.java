package com.ufrj.bd.steam.adapters.output.persistence.entities;

import com.ufrj.bd.steam.domain.models.User;
import lombok.*;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apelido")
    private String nickname;

    @Column(name = "nome")
    private String name;

    @Column(name = "url_perfil")
    private String profileUrl;

    @Column(name = "pais_origem")
    private String originCountry;

    private String avatar;

    public User toDomain() {
        return new User(
                this.id,
                this.nickname,
                this.name,
                this.profileUrl,
                this.originCountry,
                this.avatar
        );
    }
}
