package com.nnk.springboot.unitaryTest.controller;


import com.nnk.springboot.controller.RuleNameController;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.service.implentation.RuleNameService;
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
@WebMvcTest(controllers = RuleNameController.class)
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    @MockBean
    private RuleNameService ruleNameService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private RuleNameDTO ruleName1DTO;
    private RuleNameDTO ruleName2DTO;

    @BeforeEach
    public void setup(){
        ruleName1DTO = new RuleNameDTO(1,"name1", "desc1", "json1",
                                        "temp1", "sqlStr1", "sqlPart1");
        ruleName2DTO =  new RuleNameDTO(2,"name2", "desc2", "json2",
                                        "temp2", "sqlStr2", "sqlPart2");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    //---------Get----getRuleNames------/ruleName/list------------------------------------------------------------------------------------------
    @Test
    @WithMockUser(username="admin")
    @DisplayName("Test response 200 on getRuleNames")
    public void testGetRuleNames() throws Exception {
        Mockito.when(ruleNameService.getAllRuleName()).thenReturn(Arrays.asList(ruleName1DTO, ruleName2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"))
                .andExpect(view().name("ruleName/list"))
                .andExpect(status().isOk());
    }

    //---------Get----addRuleName------/ruleName/add"-----------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on addRuleName")
    public void testAddRuleName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/add"))
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }
    //---------Post----validate------/trade/validate"-------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post validate")
    public void testValidateWithRuleNameDtoWithAllParamsOk() throws Exception {
        Mockito.when(ruleNameService.addRuleName(any(RuleNameDTO.class))).thenReturn(ruleName1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .sessionAttr("ruleName", ruleName1DTO)
                .param("name",  ruleName1DTO.getName())
                .param("description",  ruleName1DTO.getDescription())
                .param("json",  ruleName1DTO.getJson())
                .param("template",  ruleName1DTO.getTemplate())
                .param("sqlStr",  ruleName1DTO.getSqlStr())
                .param("sqlPart",  ruleName1DTO.getSqlPart()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post validate with ruleNameDTO not conform")
    public void testValidateWithRuleNameDtoWithParamsEmpty() throws Exception {
        RuleNameDTO ruleNameDTONotConform = new RuleNameDTO("","","","","","");
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .sessionAttr("ruleName", ruleNameDTONotConform)
                .param("name",  ruleNameDTONotConform.getName())
                .param("description",  ruleNameDTONotConform.getDescription())
                .param("json",  ruleNameDTONotConform.getJson())
                .param("template",  ruleNameDTONotConform.getTemplate())
                .param("sqlStr",  ruleNameDTONotConform.getSqlStr())
                .param("sqlPart",  ruleNameDTONotConform.getSqlPart()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("ruleName/add"))
                .andReturn();
    }
    //--------Get----/ruleName/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 200 on showUpdate")
    public void testshowUpdateForm() throws Exception {
        Mockito.when(ruleNameService.getRuleNameById(any(Integer.class))).thenReturn(ruleName1DTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update/1"))
                .andExpect(status().isOk());
    }
    //--------Post-----updateRuleName--/ruleName/update/{id}------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test response 302/Redirection/Model-hasNoErrors on Post updateBid")
    public void testUpdateRuleNameWithDTOConform() throws Exception {
        Mockito.when(ruleNameService.updateRuleName(ruleName1DTO,1)).thenReturn(ruleName1DTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
                .sessionAttr("ruleName", ruleName1DTO)
                .param("name",  ruleName1DTO.getName())
                .param("description",  ruleName1DTO.getDescription())
                .param("json",  ruleName1DTO.getJson())
                .param("template",  ruleName1DTO.getTemplate())
                .param("sqlStr",  ruleName1DTO.getSqlStr())
                .param("sqlPart",  ruleName1DTO.getSqlPart()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test Post updateRuleName with DTO not conform")
    public void testUpdateTradeWithBidListDTONotConform() throws Exception {
        RuleNameDTO ruleNameDTONotConform = new RuleNameDTO("","","","","","");
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
                .sessionAttr("ruleName", ruleNameDTONotConform)
                .param("name",  ruleNameDTONotConform.getName())
                .param("description",  ruleNameDTONotConform.getDescription())
                .param("json",  ruleNameDTONotConform.getJson())
                .param("template",  ruleNameDTONotConform.getTemplate())
                .param("sqlStr",  ruleNameDTONotConform.getSqlStr())
                .param("sqlPart",  ruleNameDTONotConform.getSqlPart()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("ruleName/update"))
                .andReturn();
    }

    //--------Get------/ruleName/delete/{id}---------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test Get deleteRuleName responce Ok/redirect")
    public void testDeleteRuleName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/1"))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }
}
