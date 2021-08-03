package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.interfaces.IRuleNameService;
import com.nnk.springboot.tool.DtoBuilder;
import com.nnk.springboot.tool.ModelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  RuleNameService
 *
 * @See RuleName
 * @See RuleNameDTO
 * @See DtoBuilder
 * @See ModelBuilder
 */

@Service
public class RuleNameService implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Autowired
    DtoBuilder dtoBuilder;

    @Autowired
    ModelBuilder modelBuilder;

    private static Logger logger = LogManager.getLogger(RuleNameService.class);

    //------------getAllRuleName-------------------------------------------------------------------------------------
    /**
     * Method to List All RuleName
     *
     * @return List<RuleNameDTO> ruleNameDTOList
     */
    @Override
    public List<RuleNameDTO> getAllRuleName() {
       logger.info(" ---> Launch getAllRuleName");
       List<RuleNameDTO> ruleNameDTOList = new ArrayList<>();
       List<RuleName> ruleNameList = ruleNameRepository.findAll();
       for(RuleName ruleName: ruleNameList){
           ruleNameDTOList.add(dtoBuilder.buildRuleNameDTO(ruleName));
       }
       return ruleNameDTOList;
    }
    @Override
    public RuleNameDTO addRuleName(RuleNameDTO ruleNameDTO) {
        return null;
    }

    @Override
    public RuleNameDTO updateRuleName(RuleNameDTO ruleNameDTO, int id) {
        return null;
    }

    @Override
    public void deleteRuleName(int id) {

    }

    @Override
    public RuleNameDTO getRuleNameById(int id) {
        return null;
    }
}
