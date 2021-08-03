package com.nnk.springboot.service.implentation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.DataNotFoundException;
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
    //------------addRuleName-------------------------------------------------------------------------------------
    /**
     * Method to add a RuleName
     *
     * @Param  RuleNameDTO ruleNameDTO
     * @return RuleNameDTO validated return of DB
     */
    @Override
    public RuleNameDTO addRuleName(RuleNameDTO ruleNameDTO) {
        logger.info(" ---> Launch addRuleName");
        RuleName ruleName = ruleNameRepository.save(modelBuilder.buildRuleName(ruleNameDTO));
        return dtoBuilder.buildRuleNameDTO(ruleName);
    }
    //------------updateRuleName-------------------------------------------------------------------------------------
    /**
     * Method to update a RuleName
     *
     * @Param  RuleNameDTO ruleNameDTO
     * @Param  int id ( id of RuleName )
     * @return RuleNameDTO validated return of DB
     */
    @Override
    public RuleNameDTO updateRuleName(RuleNameDTO ruleNameDTO, int id) {
        logger.info(" ---> Launch updateRuleName");
        RuleName ruleNameFind = ruleNameRepository.findById(id).orElseThrow(()
                        -> new DataNotFoundException("RuleName with id=" + id + " not found in DataBase"));
        RuleName ruleName = modelBuilder.buildRuleName(ruleNameDTO);
        ruleName.setId(id);
        ruleName = ruleNameRepository.save(ruleName);
        return dtoBuilder.buildRuleNameDTO(ruleName);
    }
    //------------deleteRuleName----------------------------------------------------------------------------------
    /**
     * Void to delete RuleName with id
     *
     * @Param  int id of RuleName
     */
    @Override
    public void deleteRuleName(int id) {
        logger.info(" ---> Launch deleteRuleName");
        RuleName ruleNameFind = ruleNameRepository.findById(id).orElseThrow(()
                -> new DataNotFoundException("RuleName with id=" + id + " not found in DataBase"));
        ruleNameRepository.deleteById(id);
        logger.info("  ---> ** deleted ** RuleName with id: " + id);
    }

    @Override
    public RuleNameDTO getRuleNameById(int id) {
        return null;
    }
}
