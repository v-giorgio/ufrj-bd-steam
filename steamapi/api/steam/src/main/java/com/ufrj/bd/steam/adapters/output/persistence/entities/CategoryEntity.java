package com.ufrj.bd.steam.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categoria")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "nome")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameCategoryEntity> games = new ArrayList<>();

}
