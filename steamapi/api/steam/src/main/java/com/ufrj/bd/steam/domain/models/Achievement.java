package com.ufrj.bd.steam.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Achievement {
    private String id;
    private Double obtained_percentage;
}
