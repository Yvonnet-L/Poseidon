package com.nnk.springboot.service.interfaces;

import com.nnk.springboot.dto.RatingDTO;

import java.util.List;

public interface IRatingService {

    List<RatingDTO> getAllRating();

    RatingDTO addRating(RatingDTO ratingDTO);

    RatingDTO updateRating(RatingDTO ratingDTO, int id);

    void deleteRating(int id);

    RatingDTO getRatingById(int id);
}
