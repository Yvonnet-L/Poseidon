package com.nnk.springboot.service.interfaces;

import com.nnk.springboot.dto.RuleNameDTO;

import java.util.List;

public interface IRuleNameService {

    List<RuleNameDTO> getAllRuleName();

    RuleNameDTO addRuleName(RuleNameDTO ruleNameDTO);

    RuleNameDTO updateRuleName(RuleNameDTO ruleNameDTO, int id);

    void deleteRuleName(int id);

    RuleNameDTO getRuleNameById(int id);
}
