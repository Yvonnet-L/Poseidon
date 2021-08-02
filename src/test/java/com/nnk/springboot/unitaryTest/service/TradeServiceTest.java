package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

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
    //---------- AddTrade-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur addTrade")
    public void addTradeTest(){
        // GIVEN
        TradeDTO tradeDTOofView = new TradeDTO("account4","type4",44.44);
        Trade tradeBuild = new Trade("account4","type4",44.44);
        Trade tradeResultSave = new Trade(4,"account4","type4",44.44);
        TradeDTO tradeDTOResult = new TradeDTO(4,"account4","type4",44.44);
        // WHEN
        Mockito.when(modelBuilder.buildTrade(tradeDTOofView)).thenReturn(tradeBuild);
        Mockito.when(tradeRepository.save(tradeBuild)).thenReturn(tradeResultSave);
        Mockito.when(dtoBuilder.buildTradeDTO(tradeResultSave)).thenReturn(tradeDTOResult);
        // THEN
        assertThat(tradeService.addTrade(tradeDTOofView)).isEqualTo(tradeDTOResult);
    }
    //---------- UpdateTrade-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur updateTrade")
    public void updateTradeTest(){
        // GIVEN
        TradeDTO tradeDTOofView = new TradeDTO("account4","type4",44.44);
        Trade tradeBuild = new Trade("account4","type4",44.44);
        Trade tradeFind = new Trade(4,"account4","type4",555.44);
        Trade tradeResultSave = new Trade(4,"account4","type4",44.44);
        TradeDTO tradeDTOResult = new TradeDTO(4,"account4","type4",44.44);
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(tradeFind));
        Mockito.when(modelBuilder.buildTrade(tradeDTOofView)).thenReturn(tradeBuild);
        Mockito.when(tradeRepository.save(tradeBuild)).thenReturn(tradeResultSave);
        Mockito.when(dtoBuilder.buildTradeDTO(tradeResultSave)).thenReturn(tradeDTOResult);
        // THEN
        assertThat(tradeService.updateTrade(tradeDTOofView, 4)).isEqualTo(tradeDTOResult);
    }

    @Test
    @DisplayName("Test sur updateTrade with trade not Exist")
    public void updateTradeTestWithCurvePointNotExist(){
        // GIVEN
        TradeDTO tradeDTOofView = new TradeDTO("account4","type4",44.44);
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> tradeService.updateTrade(tradeDTOofView,any(Integer.class)));
    }
    //---------- DeleteTradeById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteTrade")
    public void deleteTradeByIdExistTest(){
        // GIVEN
        Trade tradeFind = new Trade(4,"account4","type4",555.44);
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(tradeFind));
        // THEN
        tradeService.deleteTrade(any(Integer.class));
    }
    @Test
    @DisplayName("Test sur deleteCurvePoint")
    public void deleteCurvePointByIdNotExistTest(){
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> tradeService.deleteTrade(any(Integer.class)));
    }
    //---------- GetTradeById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getTradeById with id exist")
    public void getTradeByIdExistTest(){
        // GIVEN
        Trade tradeFind = new Trade(4,"account4","type4",555.44);
        TradeDTO tradeDTOResult = new TradeDTO(4,"account4","type4",44.44);
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(tradeFind));
        Mockito.when(dtoBuilder.buildTradeDTO(any(Trade.class))).thenReturn(tradeDTOResult);
        // THEN
        assertThat(tradeService.getTradeById(any(Integer.class))).isEqualTo(tradeDTOResult);
    }

    @Test
    @DisplayName("Test sur getTradeById with id not exist")
    public void getTradeByIdNotExistTest(){
        // WHEN
        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> tradeService.getTradeById(any(Integer.class)));
    }

}
