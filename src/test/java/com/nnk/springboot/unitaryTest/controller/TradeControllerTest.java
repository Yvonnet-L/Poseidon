package com.nnk.springboot.unitaryTest.controller;


import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.controller.TradeController;
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
    @DisplayName("Test response 200 on getBidLists")
    public void testGetTrades() throws Exception {
        Mockito.when(tradeService.getAllTrade()).thenReturn(Arrays.asList(trade1DTO, trade2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(model().attributeExists("trades"))
                .andExpect(view().name("trade/list"))
                .andExpect(status().isOk());
    }


}
