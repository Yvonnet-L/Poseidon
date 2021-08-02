package com.nnk.springboot.unitaryTest.controller;


import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.controller.TradeController;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.TradeDTO;
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
@WebMvcTest(controllers = TradeController.class)
@AutoConfigureMockMvc
public class TradeControllerTest {

    @MockBean
    private TradeService tradeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private TradeDTO trade1DTO;
    private TradeDTO trade2DTO;

    @BeforeEach
    public void setup(){
        trade1DTO = new TradeDTO(1,"account1","account2",1.11);
        trade2DTO = new TradeDTO(2, "account2", "type2", 2.22);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    //---------Get----getTrades------/trade/list------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on getTrades")
    public void testGetTrades() throws Exception {
        Mockito.when(tradeService.getAllTrade()).thenReturn(Arrays.asList(trade1DTO, trade2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(model().attributeExists("trades"))
                .andExpect(view().name("trade/list"))
                .andExpect(status().isOk());
    }
    //---------Get----addTrade------/trade/add"-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addTrade")
    public void testAddTrade() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/add"))
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }
    //---------Post----validate------/trade/validate"-------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithTradeDtoWithAllParamsOk() throws Exception {
        Mockito.when(tradeService.addTrade(any(TradeDTO.class))).thenReturn(trade1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .sessionAttr("trade", trade1DTO)
                .param("account", trade1DTO.getAccount())
                .param("type", trade1DTO.getType())
                .param("buyQuantity", trade1DTO.getBuyQuantity().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with tradeDTO not conform")
    public void testValidateWithTradeDtoWithParamsEmpty() throws Exception {
        TradeDTO tradeDTONotConform = new TradeDTO("","",0.0);
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .sessionAttr("trade", tradeDTONotConform)
                .param("account", tradeDTONotConform.getAccount())
                .param("type", tradeDTONotConform.getType())
                .param("buyQuantity", tradeDTONotConform.getBuyQuantity().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("trade/add"))
                .andReturn();
    }
    //--------Get----showUpdateForm----/trade/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on showUpdate")
    public void testshowUpdateForm() throws Exception {
        Mockito.when(tradeService.getTradeById(any(Integer.class))).thenReturn(trade1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/update/1"))
                .andExpect(status().isOk());
    }
    //--------Post-----updateBid--/bidList/update/{id}------------------------------------------------------------------------------------------------
}
