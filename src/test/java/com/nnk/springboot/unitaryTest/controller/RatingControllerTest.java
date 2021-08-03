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

import static org.mockito.ArgumentMatchers.any;
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
    //---------Get----addRating------/rating/add"-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addTrade")
    public void testAddRating() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/add"))
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }
    //---------Post----validate------/rating/validate"-------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithRatingDtoWithAllParamsOk() throws Exception {
        Mockito.when(ratingService.addRating(any(RatingDTO.class))).thenReturn(rating1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .sessionAttr("rating", rating1DTO)
                .param("moodysRating", rating1DTO.getMoodysRating())
                .param("sandRating", rating1DTO.getSandRating())
                .param("fitchRating", rating1DTO.getFitchRating())
                .param("orderNumber", rating1DTO.getOrderNumber().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with ratingDTO not conform")
    public void testValidateWithRatingDtoWithParamsEmpty() throws Exception {
        RatingDTO ratingDTONotConform = new RatingDTO("","","",0);
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .sessionAttr("rating", ratingDTONotConform)
                .param("moodysRating", ratingDTONotConform.getMoodysRating())
                .param("sandRating", ratingDTONotConform.getSandRating())
                .param("fitchRating", ratingDTONotConform.getFitchRating())
                .param("orderNumber", ratingDTONotConform.getOrderNumber().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("rating/add"))
                .andReturn();
    }
    //--------Get----/rating/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on showUpdate")
    public void testshowUpdateForm() throws Exception {
        Mockito.when(ratingService.getRatingById(any(Integer.class))).thenReturn(rating1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/update/1"))
                .andExpect(status().isOk());
    }
    //--------Post-----updateTrade--/Trade/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post updateBid")
    public void testUpdateTradeWithBidListDTOConform() throws Exception {
        Mockito.when(ratingService.updateRating(rating1DTO,1)).thenReturn(rating1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                .sessionAttr("rating", rating1DTO)
                .param("moodysRating", rating1DTO.getMoodysRating())
                .param("sandRating", rating1DTO.getSandRating())
                .param("fitchRating", rating1DTO.getFitchRating())
                .param("orderNumber", rating1DTO.getOrderNumber().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post updateRating with tradeDTO not conform")
    public void testUpdateTradeWithBidListDTONotConform() throws Exception {
        RatingDTO ratingDTONotConform = new RatingDTO("","","",0);
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                .sessionAttr("rating", ratingDTONotConform)
                .param("moodysRating", ratingDTONotConform.getMoodysRating())
                .param("sandRating", ratingDTONotConform.getSandRating())
                .param("fitchRating", ratingDTONotConform.getFitchRating())
                .param("orderNumber", ratingDTONotConform.getOrderNumber().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("rating/update"))
                .andReturn();
    }


}
