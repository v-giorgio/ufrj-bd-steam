package com.ufrj.bd.steam.adapters.output.persistence.projection;

import com.ufrj.bd.steam.adapters.output.persistence.entities.GameEntity;

public interface GameAndCategoriesProjection {
    public String getCategories();
    public Long getId();
    public String getRecommendedSpec();
    public String getGenre();
    public String getDeveloperCompany();
    public String getEditorCompany();
    public String getDescription();
    public String getMinimumSpec();
    public String getName();
}
