package com.ufrj.bd.steam.adapters.input.dto;
import com.ufrj.bd.steam.adapters.output.persistence.projection.AchievementObtainedProjection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AchievementObtainedDTO {
    private String name;
    private Boolean obtained;
    private BigDecimal obtainedPercentage;

    public AchievementObtainedDTO(AchievementObtainedProjection projection) {
        this.name = projection.getName();
        this.obtained = projection.getObtained();
        this.obtainedPercentage = projection.getObtainedPercentage();
    }
    
}
