package com.nnk.springboot.unitaryTest.controller;

import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.service.implentation.BidListService;
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
@WebMvcTest(controllers = BidListController.class)
@AutoConfigureMockMvc
public class BidListControllerTest {

    @MockBean
    private BidListService bidListService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private BidListDTO bidList1DTO;
    private BidListDTO bidList2DTO;

    @BeforeEach
    public void setup(){
        bidList1DTO = new BidListDTO("account1", "type1", 25.56);
        bidList2DTO = new BidListDTO("account2", "type2", 32.64);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //---------Get----getBidLists------/bidList/list------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on getBidLists")
    public void testGetBidLists() throws Exception {
        Mockito.when(bidListService.getAllBidList()).thenReturn(Arrays.asList(bidList1DTO, bidList2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(model().attributeExists("bids"))
                .andExpect(view().name("bidList/list"))
                .andExpect(status().isOk());
    }
    //---------Get----addBidForm------/bidList/add"-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addBidForm")
    public void testaddBidForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))
                .andExpect(view().name("bidList/add"))
                .andExpect(status().isOk());
    }

    //---------Post----validate------/bidList/validate"-------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithBilistDtoWithAllParamsOk() throws Exception {
        Mockito.when(bidListService.addBidList(bidList1DTO)).thenReturn(bidList1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .sessionAttr("bid", bidList1DTO)
                .param("account", bidList1DTO.getAccount())
                .param("type", bidList1DTO.getType())
                .param("bidQuantity", bidList1DTO.getBidQuantity().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with bidListDTO not conform")
    public void testValidateWithBilistDtoWithParamsEmpty() throws Exception {
        BidListDTO bidListDTONotConform = new BidListDTO("","",0.0);
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .sessionAttr("bid", bidListDTONotConform)
                .param("account", bidListDTONotConform.getAccount())
                .param("type", bidListDTONotConform.getType())
                .param("bidQuantity", bidListDTONotConform.getBidQuantity().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("bidList/add"))
                .andReturn();
    }
    //--------Get----showUpdateForm----/bidList/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on showUpdate")
    public void testshowUpdateForm() throws Exception {
        Mockito.when(bidListService.getBidListById(any(Integer.class))).thenReturn(bidList1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update/1"))
                .andExpect(status().isOk());
    }
    //--------Post-----updateBid--/bidList/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post updateBid")
    public void testUpdateBidWithBidListDTOConform() throws Exception {
        Mockito.when(bidListService.updateBidList(bidList1DTO,1)).thenReturn(bidList1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .sessionAttr("bid", bidList1DTO)
                .param("account", bidList1DTO.getAccount())
                .param("type", bidList1DTO.getType())
                .param("bidQuantity", bidList1DTO.getBidQuantity().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post updateBid with bidListDTO not conform")
    public void testUpdateBidWithBidListDTONotConform() throws Exception {
        BidListDTO bidListDTONotConform = new BidListDTO("","",0.0);
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .sessionAttr("bid", bidListDTONotConform)
                .param("account", bidListDTONotConform.getAccount())
                .param("type", bidListDTONotConform.getType())
                .param("bidQuantity", bidListDTONotConform.getBidQuantity().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("bidList/update"))
                .andReturn();
    }

    //--------Get------/bidList/delete/{id}---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Test Get deleteBid responce Ok/redirect")
    public void testDeleteBidList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete/1"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());

    }
}
