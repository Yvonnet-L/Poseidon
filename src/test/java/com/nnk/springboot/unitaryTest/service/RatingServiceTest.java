package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.implentation.RatingService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @InjectMocks
    RatingService ratingService;

    @Mock
    RatingRepository ratingRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<Rating> ratingList;

    //---------- GetAllRating-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllRating")
    public void getAllRatingTest(){
        // GIVEN
        Rating rating1 = new Rating(1,"mooby1", "sand1" ,"fitch1", 1);
        Rating rating2 = new Rating(2,"mooby2", "sand2" ,"fitch2", 2);
        Rating rating3 = new Rating(3,"mooby3", "sand3" ,"fitch3", 3);
        ratingList = Arrays.asList(rating1, rating2, rating3);
        // WHEN
        Mockito.when(ratingRepository.findAll()).thenReturn(ratingList);
        // THEN
        assertThat(ratingService.getAllRating().size()).isEqualTo(3);
    }
    //---------- AddRating-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur addRating")
    public void addRatingTest(){
        // GIVEN
        RatingDTO ratingDTOofView = new RatingDTO("mooby1", "sand1" ,"fitch1", 1);
        Rating ratingBuild = new Rating("mooby1", "sand1" ,"fitch1", 1);
        Rating ratingResultSave = new Rating(1,"mooby1", "sand1" ,"fitch1", 1);
        RatingDTO ratingDTOResult = new RatingDTO(1,"mooby1", "sand1" ,"fitch1", 1);
        // WHEN
        Mockito.when(modelBuilder.buildRating(ratingDTOofView)).thenReturn(ratingBuild);
        Mockito.when(ratingRepository.save(ratingBuild)).thenReturn(ratingResultSave);
        Mockito.when(dtoBuilder.buildRatingDTO(ratingResultSave)).thenReturn(ratingDTOResult);
        // THEN
        assertThat(ratingService.addRating(ratingDTOofView)).isEqualTo(ratingDTOResult);
    }
    //---------- UpdateRating-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur updateRating")
    public void updateRatingTest(){
        // GIVEN
        RatingDTO ratingDTOofView = new RatingDTO("mooby1", "sand1" ,"fitch1", 1);
        Rating ratingBuild = new Rating("mooby1", "sand1" ,"fitch1", 1);
        Rating ratingResultSave = new Rating(1,"mooby1", "sand1" ,"fitch1", 1);
        Rating ratingFind = new Rating(1,"mooby1", "sand1" ,"fitch111", 1);
        RatingDTO ratingDTOResult = new RatingDTO(1,"mooby1", "sand1" ,"fitch1", 1);
        // WHEN
        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ratingFind));
        Mockito.when(modelBuilder.buildRating(ratingDTOofView)).thenReturn(ratingBuild);
        Mockito.when(ratingRepository.save(ratingBuild)).thenReturn(ratingResultSave);
        Mockito.when(dtoBuilder.buildRatingDTO(ratingResultSave)).thenReturn(ratingDTOResult);
        // THEN
        assertThat(ratingService.updateRating(ratingDTOofView, 4)).isEqualTo(ratingDTOResult);
    }

    @Test
    @DisplayName("Test sur updateRating with trade not Exist")
    public void updateRatingTestWithCurvePointNotExist(){
        // GIVEN
        RatingDTO ratingDTOofView = new RatingDTO("mooby1", "sand1" ,"fitch1", 1);
        // WHEN
        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> ratingService.updateRating(ratingDTOofView,any(Integer.class)));
    }
    //---------- DeleteRatingeById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteRating")
    public void deleteRatingByIdExistTest(){
        // GIVEN
        Rating ratingFind = new Rating(1,"mooby1", "sand1" ,"fitch111", 1);
        // WHEN
        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ratingFind));
        // THEN
        ratingService.deleteRating(any(Integer.class));
    }
    @Test
    @DisplayName("Test sur deleteRating")
    public void deleteRatingByIdNotExistTest(){
        // WHEN
        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> ratingService.deleteRating(any(Integer.class)));
    }
}
