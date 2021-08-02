package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.implentation.TradeService;
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
public class TradeServiceTest {

    @InjectMocks
    TradeService tradeService;

    @Mock
    TradeRepository tradeRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<Trade> tradeList;

    //---------- GetAllTrade-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllTrade")
    public void getAllTradeTest(){
        // GIVEN
        Trade trade1 = new Trade(1, "account1" ,"type1", 1.11);
        Trade trade2 = new Trade(2, "account2" ,"type2", 2.22);
        Trade trade3 = new Trade(3, "account3" ,"type3", 3.33);
        tradeList = Arrays.asList(trade1, trade2, trade3);
        // WHEN
        Mockito.when(tradeRepository.findAll()).thenReturn(tradeList);
        // THEN
        assertThat(tradeService.getAllTrade().size()).isEqualTo(3);
    }

}
