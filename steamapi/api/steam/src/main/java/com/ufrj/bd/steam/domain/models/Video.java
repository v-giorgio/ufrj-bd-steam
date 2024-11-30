package com.ufrj.bd.steam.domain.models;

import com.ufrj.bd.steam.adapters.output.persistence.entities.VideoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Video extends Media {
    private String name;

    public Video(Integer id, String url, String name) {
        super(id, url);
        this.name = name;
    }

    public static Video fromEntity(VideoEntity videoEntity) {
        return new Video(
                videoEntity.getId(),
                videoEntity.getUrl(),
                videoEntity.getName()
        );
    }
}
