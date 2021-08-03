package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.service.interfaces.IRuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RuleNameController {

    @Autowired
    IRuleNameService ruleNameService;

    private static Logger logger = LogManager.getLogger(RuleNameController.class);

    //---------Get-----/ruleName/list-----------------------------------------------------------
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        logger.info(" --> Launch /ruleName/list" );
        model.addAttribute("ruleNames", ruleNameService.getAllRuleName());
        return "ruleName/list";
    }
    //----------Get------/ruleName/add-----------------------------------------------------------
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleNameDTO ruleNameDTO) {
        logger.info( "--> Launch /ruleName/add" );
        return "ruleName/add";
    }
    //---------Post-----/ruleName/validate----------------------------------------------------------
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleNameDTO ruleNameDTO, BindingResult result, Model model) {
        logger.info( "--> Launch /ruleName/validate");
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            return "ruleName/add";
        }
        ruleNameService.addRuleName(ruleNameDTO);
        logger.info( "  --> **  RuleName saved **");
        return "redirect:/ruleName/list";
    }
    //----------Get-----/ruleName/update/{id}----------------------------------------------------------
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /ruleName/update/{id} with id: " + id);
        RuleNameDTO ruleNameDTO = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleNameDTO", ruleNameDTO);
        return "ruleName/update";
    }
    //----------Post-----/ruleName/update/{id}---------------------------------------------------------------
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameDTO ruleNameDTO,
                                 BindingResult result, Model model) {
        logger.info( "--> Launch Post ruleName/update/{id} with id: " + id);
        if(result.hasErrors()){
            logger.info( "  --> **  Errors ** Nb error: " + result.getErrorCount());
            model.addAttribute("ruleNameDTO", ruleNameDTO);
            model.addAttribute(id);
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(ruleNameDTO, id);
        logger.info( "  --> **  RuleName updated ** id: " + id);
        return "redirect:/ruleName/list";
    }
    //----------Get----/ruleName/delete/{id}--------------------------------------------------------------------------------------
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        logger.info( "--> Launch /ruleName/delete/{id} + id: " + id );
        ruleNameService.deleteRuleName(id);
        logger.info( "  --> **  RuleName Deleted ** id: " + id);
        return "redirect:/ruleName/list";
    }
}
