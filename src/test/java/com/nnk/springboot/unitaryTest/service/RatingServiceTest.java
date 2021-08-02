package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
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

    //---------- GetAllTrade-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllTrade")
    public void getAllTradeTest(){
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

}
