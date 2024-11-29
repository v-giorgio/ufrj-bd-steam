package com.ufrj.bd.steam.application.service;

import com.ufrj.bd.steam.adapters.input.dto.UserProfileDTO;
import com.ufrj.bd.steam.adapters.output.persistence.projection.TimePlayedByCategoryProjection;
import com.ufrj.bd.steam.adapters.output.persistence.projection.UserProfileProjection;
import com.ufrj.bd.steam.adapters.output.persistence.repository.CategoryRepository;
import com.ufrj.bd.steam.adapters.output.persistence.repository.UserRepository;
import com.ufrj.bd.steam.application.ports.input.GetUserProfileService;
import com.ufrj.bd.steam.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserProfileServiceImpl implements GetUserProfileService {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public GetUserProfileServiceImpl(
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public UserProfileDTO execute(Long userId) {
        UserProfileProjection userProfileProjection = userRepository.findUserProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));

        List<TimePlayedByCategoryProjection> topCategories = categoryRepository.findTopCategoriesByUserId(userId)
                .stream().limit(3).toList();

        return UserProfileDTO.toUserProfileDTO(userProfileProjection, topCategories);
    }
}
