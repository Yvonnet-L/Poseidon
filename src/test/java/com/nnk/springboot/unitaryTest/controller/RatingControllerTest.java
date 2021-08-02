package com.nnk.springboot.unitaryTest.controller;

import com.nnk.springboot.controller.RatingController;
import com.nnk.springboot.controller.TradeController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.service.implentation.RatingService;
import com.nnk.springboot.service.implentation.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RatingController.class)
@AutoConfigureMockMvc
public class RatingControllerTest {

    @MockBean
    private RatingService ratingService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private RatingDTO rating1DTO;
    private RatingDTO rating2DTO;

    @BeforeEach
    public void setup(){
        rating1DTO = new RatingDTO(1,"mooby1", "sand1" ,"fitch1", 1);
        rating2DTO = new RatingDTO(2,"mooby2", "sand2" ,"fitch2", 2);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    //---------Get----getRatings------/rating/list------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on getRatings")
    public void testGetRatings() throws Exception {
        Mockito.when(ratingService.getAllRating()).thenReturn(Arrays.asList(rating1DTO, rating2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("rating/list"))
                .andExpect(status().isOk());
    }

}
