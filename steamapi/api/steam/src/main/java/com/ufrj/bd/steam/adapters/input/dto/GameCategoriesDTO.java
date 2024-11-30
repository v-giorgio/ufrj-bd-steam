package com.ufrj.bd.steam.adapters.input.dto;
import java.util.Arrays;
import java.util.List;
import com.ufrj.bd.steam.adapters.output.persistence.projection.GameAndCategoriesProjection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameCategoriesDTO {
    private List<String> categories;
    private Long id;
    private String recommendedSpec;
    private String genre;
    private String developerCompany;
    private String editorCompany;
    private String description;
    private String minimumSpec;
    private String name;

    public GameCategoriesDTO(GameAndCategoriesProjection projection) {
        this.categories = Arrays.asList(projection.getCategories().split(", "));
        this.id = projection.getId();
        this.recommendedSpec = projection.getRecommendedSpec();
        this.genre = projection.getGenre();
        this.description = projection.getDescription();
        this.developerCompany = projection.getDeveloperCompany();
        this.editorCompany = projection.getEditorCompany();
        this.minimumSpec = projection.getMinimumSpec();
        this.name = projection.getName();
    }
    
}
