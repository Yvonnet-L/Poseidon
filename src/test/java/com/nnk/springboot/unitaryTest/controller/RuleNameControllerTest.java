package com.nnk.springboot.unitaryTest.controller;


import com.nnk.springboot.controller.RuleNameController;
import com.nnk.springboot.dto.RuleNameDTO;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

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
    @DisplayName("Test response 200 on getRuleNames")
    public void testGetRuleNames() throws Exception {
        Mockito.when(ruleNameService.getAllRuleName()).thenReturn(Arrays.asList(ruleName1DTO, ruleName2DTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"))
                .andExpect(view().name("ruleName/list"))
                .andExpect(status().isOk());
    }
}
