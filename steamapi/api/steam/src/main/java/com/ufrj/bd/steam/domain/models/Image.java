package com.ufrj.bd.steam.domain.models;

import com.ufrj.bd.steam.adapters.output.persistence.entities.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Image extends Media {
    private Boolean isHeader;

    public Image(Integer id, String url, Boolean isHeader) {
        super(id, url);
        this.isHeader = isHeader;
    }

    public static Image fromEntity(ImageEntity imageEntity) {
        return new Image(
                imageEntity.getId(),
                imageEntity.getUrl(),
                imageEntity.getIsHeader()
        );
    }
}
