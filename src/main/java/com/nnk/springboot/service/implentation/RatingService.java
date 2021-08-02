package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.interfaces.IRatingService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  RatingService
 *
 * @See Rating
 * @See RatingDTO
 * @See DtoBuilder
 * @See ModelBuilder
 */
@Service
public class RatingService implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    DtoBuilder dtoBuilder;

    @Autowired
    ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(RatingService.class);

    //------------getAllRating-------------------------------------------------------------------------------------
    /**
     * Method to List All Rating
     *
     * @return List<RatingDTO> ratingDTOList
     */
    @Override
    public List<RatingDTO> getAllRating() {
        logger.info(" ---> Launch getAllRating");
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratingList = ratingRepository.findAll();

        for(Rating rating: ratingList){
            ratingDTOList.add(dtoBuilder.buildRatingDTO(rating));
        }
        return ratingDTOList;
    }
    //------------addRating-------------------------------------------------------------------------------------
    /**
     * Method to add a Rating
     *
     * @Param  RatingDTO ratingDTO
     * @return RatingDTO validated return of DB
     */
    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {
        logger.info(" ---> Launch addTrade");
        Rating rating = ratingRepository.save(modelBuilder.buildRating(ratingDTO));
        return dtoBuilder.buildRatingDTO(rating);
    }

    @Override
    public RatingDTO updateRating(RatingDTO ratingDTO, int id) {
        return null;
    }

    @Override
    public void deleteRating(int id) {

    }

    @Override
    public RatingDTO getRatingById(int id) {
        return null;
    }
}
