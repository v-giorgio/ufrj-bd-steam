package com.ufrj.bd.steam.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Media {
    private Integer id;
    private String url;
}
