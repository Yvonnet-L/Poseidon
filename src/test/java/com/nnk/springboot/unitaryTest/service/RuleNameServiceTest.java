package com.nnk.springboot.unitaryTest.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.implentation.RuleNameService;
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
public class RuleNameServiceTest {

    @InjectMocks
    RuleNameService ruleNameService;

    @Mock
    RuleNameRepository ruleNameRepository;

    @Mock
    DtoBuilder dtoBuilder;

    @Mock
    ModelBuilder modelBuilder;

    private List<RuleName> ruleNameList;

    //---------- GetAllRuleName-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getAllRuleName")
    public void getAllRuleNameTest(){
        // GIVEN
        RuleName ruleName1 = new RuleName(1,"name1", "desc1", "json1", "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleName2 = new RuleName(2,"name2", "desc2", "json2", "temp2", "sqlStr2", "sqlPart2");
        RuleName ruleName3 = new RuleName(3,"name3", "desc3", "json3", "temp3", "sqlStr3", "sqlPart3");
        ruleNameList= Arrays.asList(ruleName1, ruleName2, ruleName3);
        // WHEN
        Mockito.when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        // THEN
        assertThat(ruleNameService.getAllRuleName().size()).isEqualTo(3);
    }
    //---------- AddRuleName-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur addRuleName")
    public void addRuleNameTest(){
        // GIVEN
        RuleNameDTO ruleNameDTOofView = new RuleNameDTO("name1", "desc1", "json1",
                                                             "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleNameBuild = new RuleName("name1", "desc1", "json1",
                                                      "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleNameResultSave = new RuleName("name1", "desc1", "json1",
                                                       "temp1", "sqlStr1", "sqlPart1");
        RuleNameDTO ruleNameDTOResult = new RuleNameDTO(1,"name1", "desc1", "json1",
                                                         "temp1", "sqlStr1", "sqlPart1");
        // WHEN
        Mockito.when(modelBuilder.buildRuleName(ruleNameDTOofView)).thenReturn(ruleNameBuild);
        Mockito.when(ruleNameRepository.save(ruleNameBuild)).thenReturn(ruleNameResultSave);
        Mockito.when(dtoBuilder.buildRuleNameDTO(ruleNameResultSave)).thenReturn(ruleNameDTOResult);
        // THEN
        assertThat(ruleNameService.addRuleName(ruleNameDTOofView)).isEqualTo(ruleNameDTOResult);
    }
    //---------- UpdateRuleName-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur updateRuleName")
    public void updateRuleNameTest(){
        // GIVEN
        RuleNameDTO ruleNameDTOofView = new RuleNameDTO("name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleNameFind = new RuleName(1,"name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleNameBuild = new RuleName("name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        RuleName ruleNameResultSave = new RuleName("name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        RuleNameDTO ruleNameDTOResult = new RuleNameDTO(1,"name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ruleNameFind));
        Mockito.when(modelBuilder.buildRuleName(ruleNameDTOofView)).thenReturn(ruleNameBuild);
        Mockito.when(ruleNameRepository.save(ruleNameBuild)).thenReturn(ruleNameResultSave);
        Mockito.when(dtoBuilder.buildRuleNameDTO(ruleNameResultSave)).thenReturn(ruleNameDTOResult);
        // THEN
        assertThat(ruleNameService.updateRuleName(ruleNameDTOofView, 4)).isEqualTo(ruleNameDTOResult);
    }

    @Test
    @DisplayName("Test sur updateRuleName with ruleName not Exist")
    public void updateRuleNameTestWithRuleNameNotExist(){
        // GIVEN
        RuleNameDTO ruleNameDTOofView = new RuleNameDTO("name1", "desc1", "json1",
                "temp1", "sqlStr1", "sqlPart1");
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> ruleNameService.updateRuleName(ruleNameDTOofView,any(Integer.class)));
    }
    //---------- DeleteRuleNameById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur deleteRuleName with ruleName exist")
    public void deleteRuleNameByIdExistTest(){
        // GIVEN
        RuleName ruleNameFind = new RuleName("name1", "desc1", "json1",
                                                "temp1", "sqlStr1", "sqlPart1");
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ruleNameFind));
        // THEN
        ruleNameService.deleteRuleName(any(Integer.class));
    }
    @Test
    @DisplayName("Test sur deleteRuleName with ruleName not exist")
    public void deleteRuleNameByIdNotExistTest(){
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> ruleNameService.deleteRuleName(any(Integer.class)));
    }
    //---------- GetRuleNameById-----------------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test sur getRuleNameById with id exist")
    public void getRuleNameByIdExistTest(){
        // GIVEN
        RuleName ruleNameFind = new RuleName(1,"name1", "desc1", "json1",
                                                "temp1", "sqlStr1", "sqlPart1");
        RuleNameDTO ruleNameDTOResult = new RuleNameDTO(1,"name1", "desc1", "json1",
                                                            "temp1", "sqlStr1", "sqlPart1");
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ruleNameFind));
        Mockito.when(dtoBuilder.buildRuleNameDTO(any(RuleName.class))).thenReturn(ruleNameDTOResult);
        // THEN
        assertThat(ruleNameService.getRuleNameById(any(Integer.class))).isEqualTo(ruleNameDTOResult);
    }

    @Test
    @DisplayName("Test sur getRuleNameById with id not exist")
    public void getRuleNameByIdNotExistTest(){
        // WHEN
        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());
        // THEN
        assertThrows(DataNotFoundException.class, () -> ruleNameService.getRuleNameById(any(Integer.class)));
    }
}
