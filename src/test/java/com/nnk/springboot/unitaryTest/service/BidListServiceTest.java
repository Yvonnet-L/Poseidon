package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.implentation.BidListService;
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
public class BidListServiceTest {

    @InjectMocks
    BidListService bidListService;

    @Mock
    BidListRepository bidListRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<BidList> bidLists;

    //---------- getAllBidList-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllBidList")
    public void getAllBidListTest(){
        // GIVEN
        BidList bidList1 = new BidList(1 , "account1", "type1", 1.11);
        BidList bidList2 = new BidList(2 , "account2", "type2", 2.22);
        BidList bidList3 = new BidList(3 , "account3", "type3", 3.33);
        bidLists = Arrays.asList(bidList1, bidList2, bidList3);
        // WHEN
        Mockito.when(bidListRepository.findAll()).thenReturn(bidLists);
        // THEN
        assertThat(bidListService.getAllBidList().size()).isEqualTo(3);
    }
    //------------AddBidList--------------------------------------------------------------------------------------------------------------
    @Test
    public void addBidListTest(){
        // GIVEN
        BidListDTO bidListViewDTO = new BidListDTO( "account1", "type1", 1.11);
        BidListDTO bidListResultDTO = new BidListDTO(1 , "account1", "type1", 1.11);
        BidList bidList1 = new BidList( "account1", "type1", 1.11);
        // WHEN
        Mockito.when(modelBuilder.buildBidList(any(BidListDTO.class))).thenReturn(bidList1);
        Mockito.when(bidListRepository.save(any(BidList.class))).thenReturn(bidList1);
        Mockito.when(dtoBuilder.buildUBidListDTO(any(BidList.class))).thenReturn(bidListResultDTO);
        // THEN
        assertThat(bidListService.addBidList(bidListViewDTO)).isEqualTo( bidListResultDTO);

    }
    //-------------UpdateBidList-------------------------------------------------------------------------------------------------------------
    @Test
    public void updateBidListExistTest (){
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO( "account1", "type1", 1.11);
        BidList bidList = new BidList( "account1", "type1", 1.11);
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(bidList));
        Mockito.when(modelBuilder.buildBidList(bidListDTO)).thenReturn(bidList);
        Mockito.when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);
        Mockito.when(dtoBuilder.buildUBidListDTO(any(BidList.class))).thenReturn(bidListDTO);
        // THEN
        assertThat(bidListService.updateBidList(bidListDTO, any(Integer.class))).isEqualTo(bidListDTO);

    }

    @Test
    public void updateBidListNoExistTest (){
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO( "account1", "type1", 1.11);
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> bidListService.updateBidList(bidListDTO,any(Integer.class)));
    }

    //--------------DeleteBidList---------------------------------------------------------------------------------------------------
    @Test
    public void deleteBidListByIdExisTest(){
        // GIVEN
        BidList bidList = new BidList( "account1", "type1", 1.11);
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(bidList));
        // THEN
        bidListService.deleteBidList(any(Integer.class));

    }

    @Test
    public void deleteBidListByIdNoExisTest(){
        // GIVEN
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> bidListService.deleteBidList(any(Integer.class)));
    }

    //--------------GetUserById--------------------------------------------------------------------------------------
    @Test
    public void getBidListByIdExistTest() {
        // GIVEN
        BidList bidList = new BidList( "account1", "type1", 1.11);
        BidListDTO bidListDTO = new BidListDTO( "account1", "type1", 1.11);
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(bidList));
        Mockito.when(dtoBuilder.buildUBidListDTO(any(BidList.class))).thenReturn(bidListDTO);
        // THEN
        assertThat(bidListService.getBidListById(any(int.class))).isEqualTo(bidListDTO);
    }

    @Test
    public void getBidListByIdNotExistTest() {
        // GIVEN
        // WHEN
        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> bidListService.getBidListById(any(Integer.class)));
    }
}
