package com.nnk.springboot.unitaryTest.controller;

import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.controller.CurveController;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.service.interfaces.ICurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CurveController.class)
@AutoConfigureMockMvc
public class CurveControllerTest {

    @MockBean
    private ICurvePointService curvePointService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private CurvePointDTO curvePoint1DTO;
    private CurvePointDTO curvePoint2DTO;

    @BeforeEach
    public void setup(){
        curvePoint1DTO = new CurvePointDTO(1,1.11,1.11);
        curvePoint2DTO = new CurvePointDTO(2,2.22,2.22);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    //---------Get----home------/curvePoint/list------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username="admin")
    @DisplayName("Test response 200 on getBidLists")
    public void testHome() throws Exception {
        Mockito.when(curvePointService.getAllCurvePoint()).thenReturn(Arrays.asList(curvePoint1DTO, curvePoint2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(model().attributeExists("curves"))
                .andExpect(view().name("curvePoint/list"))
                .andExpect(status().isOk());
    }
    //---------Get----addCurvePoint------/curvePoint/add"-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addCurvePoint")
    public void testaddCurvePointForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add"))
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    //---------Post----validate------/curvePoint/validate"-------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithCurvePointDtoWithAllParamsOk() throws Exception {
        Mockito.when(curvePointService.addCurvePoint(curvePoint1DTO)).thenReturn(curvePoint1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .sessionAttr("curvePointDTO", curvePoint1DTO)
                .param("curveId", curvePoint1DTO.getCurveId().toString())
                .param("term", curvePoint1DTO.getTerm().toString())
                .param("value", curvePoint1DTO.getValue().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with CurvePointDTO not conform")
    public void testValidateWithCurvePointDtoWithParamsNotConform() throws Exception {
        CurvePointDTO curvePointDTONotConform = new CurvePointDTO(0,1.2354,1.54564);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
               .sessionAttr("curvePointDTO", curvePointDTONotConform)
                .param("curveId", curvePointDTONotConform.getCurveId().toString())
                .param("term", curvePointDTONotConform.getTerm().toString())
                .param("value", curvePointDTONotConform.getValue().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("curvePoint/add"))
                .andReturn();
    }

    //--------Get----showUpdateForm----/curvePoint/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on showUpdate")
    public void testShowUpdateForm() throws Exception {
        Mockito.when(curvePointService.getCurvePointById(any(Integer.class))).thenReturn(curvePoint1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1"))
                .andExpect(status().isOk());
    }

    //--------Post-----updateCurvePoint--/curvePoint/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post updateBid")
    public void testUpdateBidWithCurvePointDTOConform() throws Exception {
        Mockito.when(curvePointService.updateCurvePoint(curvePoint1DTO,1)).thenReturn(curvePoint1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .sessionAttr("curvePointDTO", curvePoint1DTO)
                .param("curveId", curvePoint1DTO.getCurveId().toString())
                .param("term", curvePoint1DTO.getTerm().toString())
                .param("value", curvePoint1DTO.getValue().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post updateBCurvePoint with curvePointDTO not conform")
    public void testUpdateBidWithCurvePointDTONotConform() throws Exception {
        CurvePointDTO curvePointDTONotConform = new CurvePointDTO(0,1.2354,1.54564);
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .sessionAttr("curvePointDTO", curvePointDTONotConform)
                .param("curveId", curvePointDTONotConform.getCurveId().toString())
                .param("term", curvePointDTONotConform.getTerm().toString())
                .param("value", curvePointDTONotConform.getValue().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("curvePoint/update"))
                .andReturn();
    }

    //--------Get------/curvePoint/delete/{id}---------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Test Get deleteCurvePoint responce Ok/redirect")
    public void testDeleteCurvePoint() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/1"))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());

    }

}
