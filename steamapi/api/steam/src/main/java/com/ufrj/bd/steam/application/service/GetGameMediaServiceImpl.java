package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.input.dto.GameMediaDTO;
import com.ufrj.bd.steam.adapters.output.persistence.repository.ImageRepository;
import com.ufrj.bd.steam.adapters.output.persistence.repository.VideoRepository;
import com.ufrj.bd.steam.application.ports.input.GetGameMediaService;
import com.ufrj.bd.steam.domain.models.Image;
import com.ufrj.bd.steam.domain.models.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGameMediaServiceImpl implements GetGameMediaService {

    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;

    public GetGameMediaServiceImpl(ImageRepository imageRepository, VideoRepository videoRepository) {
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
    }


    @Override
    public GameMediaDTO execute(Long gameId) {
        List<Image> images = imageRepository.findByGameId(gameId).stream().map(Image::fromEntity).toList();
        List<Video> videos = videoRepository.findByGameId(gameId).stream().map(Video::fromEntity).toList();

        return GameMediaDTO.toGameMediaDTO(images, videos);
    }
}
