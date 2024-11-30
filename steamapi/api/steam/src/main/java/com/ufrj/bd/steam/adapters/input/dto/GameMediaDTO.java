package com.ufrj.bd.steam.adapters.input.dto;

import com.ufrj.bd.steam.domain.models.Image;
import com.ufrj.bd.steam.domain.models.Video;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GameMediaDTO {

    private List<VideoDTO> videos;
    private List<ImageDTO> images;

    @Data
    @AllArgsConstructor
    public static class VideoDTO {
        private String name;
        private String url;
    }

    @Data
    @AllArgsConstructor
    public static class ImageDTO {
        private String url;
        private Boolean isHeader;
    }

    public static GameMediaDTO toGameMediaDTO(
            List<Image> images,
            List<Video> videos
    ) {
        List<ImageDTO> imageDTOs = images.stream()
                .map(image -> new ImageDTO(image.getUrl(), image.getIsHeader()))
                .collect(Collectors.toList());

        List<VideoDTO> videoDTOs = videos.stream()
                .map(video -> new VideoDTO(video.getName(), video.getUrl()))
                .collect(Collectors.toList());

        return new GameMediaDTO(videoDTOs, imageDTOs);
    }
}
